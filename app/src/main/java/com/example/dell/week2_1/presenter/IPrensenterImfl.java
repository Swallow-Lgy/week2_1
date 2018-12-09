package com.example.dell.week2_1.presenter;

import com.example.dell.week2_1.callback.MyCallBack;
import com.example.dell.week2_1.model.IModelImpl;
import com.example.dell.week2_1.view.IView;

public class IPrensenterImfl implements IPresenter{
    private IView miView;
    private IModelImpl miModelimpl;
    public IPrensenterImfl(IView iView){
        miView = iView;
        miModelimpl = new IModelImpl();
    }
    @Override
    public void requestData(String url, Class clazz) {
        miModelimpl.requestData(url, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                miView.success(data);
            }
        });
    }
    public void des(){
        if (miModelimpl!=null){
            miModelimpl=null;
        }
        if (miView!=null){
            miView=null;
        }
    }
}
