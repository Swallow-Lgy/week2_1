package com.example.dell.week2_1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.adpter.MyBaseAdpter;
import com.example.dell.week2_1.bean.ImageBean;
import com.example.dell.week2_1.bean.NewBean;
import com.example.dell.week2_1.presenter.IPrensenterImfl;
import com.example.dell.week2_1.view.IView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

public class FragmentHome extends Fragment implements View.OnClickListener,IView {
    private FlyBanner flyBanner;
    private IPrensenterImfl miPrensenterImfl;
    private String flyurl = "http://www.zhaoapi.cn/ad/getAd?token=05329B0DCBE400ED03760D7B27627FC7";
     private String newfly="http://www.xieast.com/api/news/news.php?pager=1";
     private int pager=1;
    private List<String> image;
    private MyBaseAdpter adpter;
    private XListView xlistView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome,container,false);
        flyBanner = view.findViewById(R.id.fly);
        xlistView = view.findViewById(R.id.xlist);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        miPrensenterImfl = new IPrensenterImfl(this);
        adpter = new MyBaseAdpter(getActivity());
        miPrensenterImfl.requestData(flyurl,ImageBean.class);
        image = new ArrayList<>();
        xlistView.setPullLoadEnable(true);
        xlistView.setPullRefreshEnable(true);
        xlistView.setAdapter(adpter);
        xlistView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                pager=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });
        loadData();
    }
    public void loadData(){
        miPrensenterImfl.requestData(String.format(newfly,pager),NewBean.class);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
        if (data instanceof ImageBean){
            ImageBean bean = (ImageBean) data;
            List<ImageBean.DataBean> data1 = bean.getData();
            for (int i=0;i<data1.size();i++){
                String url = data1.get(i).getImage();
                image.add(url);
            }
            flyBanner.setImagesUrl(image);
        }
       else {
            NewBean newBean = (NewBean) data;
            if (pager==1){
                adpter.setmData(newBean.getData());
            }
            else {
                adpter.addmData(newBean.getData());
            }
            pager++;
        }

    }
}
