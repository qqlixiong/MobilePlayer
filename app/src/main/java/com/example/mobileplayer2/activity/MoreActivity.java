package com.example.mobileplayer2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.holder.MoreHolder;
import com.example.mobileplayer2.interfaces.IStatusBar;
import com.example.mobileplayer2.utils.Config;
import com.example.mobileplayer2.utils.Constants;
import com.example.mobileplayer2.utils.RecyclerViewUtil;

public class MoreActivity extends AppCompatActivity implements Config, View.OnClickListener,IStatusBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        RecyclerViewUtil.getInstance().initRecyclerView((RecyclerView) findViewById(R.id.rv_more), Constants.getMoreList(this),new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false), MoreHolder.class);
        findViewById(R.id.imageView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
