package com.ichsy.hrys.model.main.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ContentTaskModelInfo;
import com.ichsy.hrys.model.main.ItemContentTaskFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zz.mk.utilslibrary.LogUtil;

/**
 * author: ihesen on 2016/12/2 11:34
 * email: hesen@ichsy.com
 */

public class ContentPagerAdapter extends FragmentPagerAdapter {

    private List<ContentTaskModelInfo> taskModelList;
    private ItemContentTaskFragment currentFragment;
    private int noNeedRequestPosition;
    private Map<Integer, Fragment> fragments = new HashMap<>();

    public ContentPagerAdapter(FragmentManager fragmentManager, List<ContentTaskModelInfo> taskModelList) {
        super(fragmentManager);
        this.taskModelList = taskModelList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return taskModelList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment1 = fragments.get(position);
        if(fragment1 != null){
            return fragment1;
        }else{
            ItemContentTaskFragment fragment = new ItemContentTaskFragment();
            Bundle bundle = new Bundle();
            bundle.putString(StringConstant.ITEM_TYPE, taskModelList.get(position).getType());
            LogUtil.zLog().e("ihesen: position:" + position + " fragment:" + fragment);
            if(position == noNeedRequestPosition){
                bundle.putBoolean(StringConstant.NEED_REQUEST, false);
            }
            fragment.setArguments(bundle);
            fragments.put(position, fragment);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return taskModelList.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (ItemContentTaskFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public ItemContentTaskFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setNoRequestPosition(int position){
        this.noNeedRequestPosition = position;
    }
}