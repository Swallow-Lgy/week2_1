package com.example.dell.week2_1.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.dell.week2_1.callback.MyCallBack;
import com.example.dell.week2_1.util.NetUtil;
import com.google.gson.Gson;

public class IModelImpl implements IModel{
    @SuppressLint("StaticFieldLeak")
    @Override
    public void requestData(String url, final Class clazz, final MyCallBack callBack) {
          new AsyncTask<String, Void, Object>() {
              @Override
              protected Object doInBackground(String... strings) {
                  return getGson(strings[0],clazz);
              }

              @Override
              protected void onPostExecute(Object o) {
                  callBack.setData(o);
              }
          }.execute(url);
    }
    public <T> T getGson(String url,Class clazz){
        return (T) new Gson().fromJson(NetUtil.getData(url),clazz);
    }
}
