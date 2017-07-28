package com.example.mobileplayer2.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.base.BasePager;
import com.example.mobileplayer2.business.MyOnCheckedChangeListener;
import com.example.mobileplayer2.interfaces.IStatusBar;
import com.example.mobileplayer2.pager.AudioPager;
import com.example.mobileplayer2.pager.FragmentPager;
import com.example.mobileplayer2.pager.NetAudioPager;
import com.example.mobileplayer2.pager.NetVideoPager;
import com.example.mobileplayer2.interfaces.IPager;
import com.example.mobileplayer2.pager.VideoPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Admin on 2016/7/16 10:26
 * ""
 * ""
 * 作用：主页面
 */
public class MainActivity extends FragmentActivity implements IPager ,IStatusBar{

    private RadioGroup  rg_bottom_tag;
    /**
     * 是否已经退出
     */
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);

        //设置RadioGroup的监听
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener(getSupportFragmentManager()));
        rg_bottom_tag.check(R.id.rb_video);//默认选中首页

    }

    @Override
    public List<BasePager> getBasePagerList() {
        /**
         * 页面的集合
         */
        List<BasePager> basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));//添加本地视频页面-0
        basePagers.add(new AudioPager(this));//添加本地音乐页面-1
        basePagers.add(new NetVideoPager(this));//添加网络视频页面-2
        basePagers.add(new NetAudioPager(this));//添加网络音频页面-3
        return basePagers;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            if(!isExit){
                isExit = true;
                Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit  = false;
                    }
                },2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
