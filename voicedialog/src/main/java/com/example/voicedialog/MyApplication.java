package com.example.voicedialog;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * 作者：Admin on 2016/3/24 23:23
 * ""
 * ""
 * 作用：代表整个应用
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56f4c1dd");
    }
}
