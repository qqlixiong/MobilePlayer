package com.example.mobileplayer2.more.robot.utils;

import android.content.Context;

import com.example.mobileplayer2.more.robot.bean.SpeechBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */

public class UIUtils {
    public static String[] getString(Context context,int... resIds){
        String[] strings = new String[resIds.length];
        for (int i=0;i<resIds.length;i++){
            strings[i] = context.getResources().getString(resIds[i]);
        }
        return strings;
    }

    /**
     * 解析json
     * @param resultString
     * @return
     */
    public static String getVoice(String resultString) {
        Gson gson = new Gson();
        SpeechBean voice = gson.fromJson(resultString, SpeechBean.class);
        List<SpeechBean.WS> ws = voice.ws;
        StringBuffer sb = new StringBuffer();
        for (SpeechBean.WS wsBean : ws) {
            String str = wsBean.cw.get(0).w;
            sb.append(str);
        }
        return sb.toString();
    }
}
