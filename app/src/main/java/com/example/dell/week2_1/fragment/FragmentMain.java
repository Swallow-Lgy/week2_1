package com.example.dell.week2_1.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.view.LoginActivity;
import com.example.dell.week2_1.view.SaoActivity;

import java.io.BufferedReader;
import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class FragmentMain extends Fragment implements View.OnClickListener{
   private Button button;
   private ImageView icon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentmain,container,false);
        button = view.findViewById(R.id.but);
        icon = view.findViewById(R.id.icon);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       creatQRCode();
    }

    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.but:
                 Intent intent = new Intent(getActivity(),SaoActivity.class);
                 getActivity().startActivity(intent);
                 break;
                 default:
                     break;
         }
    }
    private void creatQRCode(){
        String name = ((LoginActivity) getActivity()).getName();
        TaskQCode taskQCode = new TaskQCode(getActivity(),icon,name);
        taskQCode.execute(name);
    }
   static class TaskQCode extends AsyncTask<String,Void,Bitmap>{
       private WeakReference<Context> mContext;
       private WeakReference<ImageView> mImageView;
       public TaskQCode(Context context,ImageView imageView,String name){
          mContext = new WeakReference<>(context);
          mImageView = new WeakReference<>(imageView);
       }
       @Override
       protected Bitmap doInBackground(String... strings) {
           if (TextUtils.isEmpty(strings[0])){
               return null;
           }
          int size  = mContext.get().getResources().getDimensionPixelOffset(R.dimen.code_sze);
         return QRCodeEncoder.syncEncodeQRCode(strings[0],size);
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           if (bitmap!=null){
               mImageView.get().setImageBitmap(bitmap);
           }
           else {
               Toast.makeText(mContext.get(),"生成失败",Toast.LENGTH_SHORT).show();
           }
       }
   }
}
