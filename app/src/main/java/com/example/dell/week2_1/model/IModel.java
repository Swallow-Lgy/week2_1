package com.example.dell.week2_1.model;

import com.example.dell.week2_1.callback.MyCallBack;

public interface IModel {
    void requestData(String url, Class clazz, MyCallBack callBack);
}
