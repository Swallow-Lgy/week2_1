package com.example.dell.week2_1.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.bean.UserBean;
import com.example.dell.week2_1.presenter.IPrensenterImfl;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText phonenum,password;
    private Button login,reg,threelogin;
    private IPrensenterImfl miPrensenterImfl;
    private CheckBox remcheck,regcheck;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String loginurl="http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        phonenum = findViewById(R.id.phonenum);
        password  = findViewById(R.id.password);
        remcheck = findViewById(R.id.remcheck);
        regcheck = findViewById(R.id.regcheck);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        threelogin  = findViewById(R.id.threelogin);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
        threelogin.setOnClickListener(this);
        miPrensenterImfl = new IPrensenterImfl(this);
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean r_ischeck = sharedPreferences.getBoolean("r_ischeck", false);
        if (r_ischeck){
            String num = sharedPreferences.getString("num", null);
            String pws = sharedPreferences.getString("pws", null);
            phonenum.setText(num);
            password.setText(pws);
            remcheck.setChecked(true);
        }
        boolean g_ischeck = sharedPreferences.getBoolean("g_ischeck", false);
        if (g_ischeck){
            regcheck.setChecked(true);
            miPrensenterImfl.requestData(String.format(loginurl,phonenum.getText().toString(),password.getText().toString()),UserBean.class);
        }
    }


    //登录回调

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.login:
                if (remcheck.isChecked()){
                    editor.putString("num",phonenum.getText().toString());
                    editor.putString("pws",password.getText().toString());
                    editor.putBoolean("r_ischeck",true);
                    editor.commit();
                }
                else {
                    editor.clear();
                    editor.commit();
                }
                if (regcheck.isChecked()){
                    editor.putBoolean("g_ischeck",true);
                    editor.commit();
                }
              miPrensenterImfl.requestData(String.format(loginurl,phonenum.getText().toString(),password.getText().toString()),UserBean.class);
              break;
            case R.id.reg:
                Intent intent = new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);
                break;
            case R.id.threelogin:
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("LGY",map+"");
                        Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });

                break;
           default:
               break;
        }

    }

    @Override
    public void success(Object data) {
        UserBean bean = (UserBean) data;
        if (bean.getCode().equals("0")){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.putExtra("name",phonenum.getText().toString());
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        miPrensenterImfl.des();
    }
}
