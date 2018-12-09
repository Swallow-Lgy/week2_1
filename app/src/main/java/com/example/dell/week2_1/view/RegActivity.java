package com.example.dell.week2_1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.bean.RegBean;
import com.example.dell.week2_1.presenter.IPrensenterImfl;

public class RegActivity extends AppCompatActivity implements View.OnClickListener,IView{
    private EditText regphonenum,regpassword;
    private Button register;
    private IPrensenterImfl miPrensenterImfl;
    private String regUrl="http://www.zhaoapi.cn/user/reg?mobile=%s&password=%s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
    }

    private void init() {
        register =  findViewById(R.id.register);
        register.setOnClickListener(this);
        regphonenum = findViewById(R.id.regphonenum);
        regpassword = findViewById(R.id.regpassword);
        //互绑
        miPrensenterImfl = new IPrensenterImfl(this);
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        miPrensenterImfl.des();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.register:
                miPrensenterImfl.requestData(String.format(regUrl,regphonenum.getText().toString(),regpassword.getText().toString()),RegBean.class);
        }

    }

    @Override
    public void success(Object data) {
        RegBean bean = (RegBean) data;
        if (bean.getCode().equals("0")){
            Intent intent  = new Intent(RegActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(RegActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }
}
