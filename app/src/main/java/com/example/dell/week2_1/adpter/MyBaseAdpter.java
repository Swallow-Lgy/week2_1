package com.example.dell.week2_1.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.bean.NewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyBaseAdpter extends BaseAdapter {
      private List<NewBean.DataBean> mData;
      private Context mContext;

    public MyBaseAdpter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public void setmData(List<NewBean.DataBean> data) {
        mData.clear();
        if (data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addmData(List<NewBean.DataBean> data) {

        if (data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NewBean.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = View.inflate(mContext, R.layout.item,null);
            holder.icon = convertView.findViewById(R.id.icon);
            holder.title = convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).getTitle());
        ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.icon);
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView title;

    }
}
