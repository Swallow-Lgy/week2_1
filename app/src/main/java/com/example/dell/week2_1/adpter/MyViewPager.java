package com.example.dell.week2_1.adpter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.week2_1.fragment.FragmentHome;
import com.example.dell.week2_1.fragment.FragmentMain;

public class MyViewPager extends FragmentPagerAdapter {
    String[] pager = new String[]{
            "首页","我的"
    };
    public MyViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentMain();
                default:
                    break;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pager[position];
    }

    @Override
    public int getCount() {
        return pager.length;
    }
}
