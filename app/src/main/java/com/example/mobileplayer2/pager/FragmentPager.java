package com.example.mobileplayer2.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileplayer2.base.BasePager;
import com.example.mobileplayer2.interfaces.IPager;
import com.example.mobileplayer2.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */

public class FragmentPager extends Fragment{
    /**
     * 页面的集合
     */
    private List<BasePager> basePagers;
    private int position;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IPager){
            basePagers = ((IPager)context).getBasePagerList();
        }
    }

    public static FragmentPager newInstance(int position){
        FragmentPager fragmentPager = new FragmentPager();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.PAGE_POSITION,position);
        fragmentPager.setArguments(bundle);
        return fragmentPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(Constants.PAGE_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BasePager basePager = getBasePager();
        if(basePager != null){
            //各个页面的视图
            return basePager.rootView;
        }
        return null;
    }

    /**
     * 根据位置得到对应的页面
     * @return
     */
    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if(basePager != null&&!basePager.isInitData){
            basePager.initData();//联网请求或者绑定数据
            basePager.isInitData = true;
        }
        return basePager;
    }
}
