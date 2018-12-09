package com.example.dell.week2_1.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.week2_1.R;
import com.example.dell.week2_1.adpter.MyViewPager;
import com.example.dell.week2_1.bean.RegBean;
import com.example.dell.week2_1.presenter.IPrensenterImfl;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPager pager;
    private  String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       init();
    }

    private void init() {
        viewPager =findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab);
         pager = new MyViewPager(getSupportFragmentManager());
         viewPager.setAdapter(pager);
         tabLayout.setupWithViewPager(viewPager);
         Intent intent = getIntent();
        name   = intent.getStringExtra("name");
    }
   public String getName(){
        return name;
   }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {

    }
}
