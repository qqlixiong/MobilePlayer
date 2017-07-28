package com.example.mobileplayer2.utils;

import android.content.Context;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.bean.MoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚硅谷-Admin on 2016/7/22 09:21
 * ""
 * ""
 * 作用：常量类，配置网络地址
 */
public class Constants {
    /**
     * 页面的位置
     */
    public static final String PAGE_POSITION = "pager_position";

    /**
     * 网络视频的联网地址
     */
    public static  final  String NET_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    /**
     * 搜索的路径
     */
    public static  final  String SEARCH_URL = "http://hot.news.cntv.cn/index.php?controller=list&action=searchList&sort=date&n=20&wd=";


    /**
     * 网络音乐
     */
    public static final String ALL_RES_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
//  

    public static final List<MoreBean> getMoreList(Context context){
        List<MoreBean> list = new ArrayList<>();
        MoreBean moreBean = new MoreBean();
        moreBean.setFont(context.getResources().getString(R.string.more_font_1));
        moreBean.setName("直播室");
        list.add(moreBean);
        return list;
    }
}
