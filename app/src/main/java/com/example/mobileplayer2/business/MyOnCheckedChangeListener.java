package com.example.mobileplayer2.business;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.pager.FragmentPager;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
    /**
     * 选中的位置
     */
    private int position;
    private FragmentManager manager;

    public MyOnCheckedChangeListener(FragmentManager manager) {
        this.manager = manager;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            default:
                position = 0;
                break;
            case R.id.rb_audio://音频
                position = 1;
                break;
            case R.id.rb_net_video://网络视频
                position = 2;
                break;
            case R.id.rb_netaudio://网络音频
                position = 3;
                break;
        }

        setFragment();
    }

    /**
     * 把页面添加到Fragment中
     */
    private void setFragment() {
        //1.获取FragmentManager
        //2.开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //3.替换
        ft.replace(R.id.fl_main_content, FragmentPager.newInstance(position));
        //4.提交事务
        ft.commit();

    }
}
