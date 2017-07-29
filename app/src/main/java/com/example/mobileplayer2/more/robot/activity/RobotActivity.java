package com.example.mobileplayer2.more.robot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.more.robot.adapter.RobotAdapter;
import com.example.mobileplayer2.more.robot.listener.RobotRecognizerDialogListener;
import com.example.mobileplayer2.more.robot.bean.ConversationInfo;
import com.example.mobileplayer2.more.robot.utils.SpeechUtils;

import java.util.ArrayList;
import java.util.List;

public class RobotActivity extends AppCompatActivity implements View.OnClickListener {
    private SpeechUtils mSpeechUtils;
    private List<ConversationInfo> conversationList;
    private RobotAdapter mAdapter;
    private ListView mListView;
    private Button btnStartListen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mSpeechUtils = SpeechUtils.getInstance(this);
    }

    private void initView() {
        setContentView(R.layout.activity_robot);
        mListView = (ListView) findViewById(R.id.lv_conversation);
        btnStartListen = (Button) findViewById(R.id.btn_start_listen);
        btnStartListen.setOnClickListener(this);
        conversationList = new ArrayList<>();
        mAdapter = new RobotAdapter(RobotActivity.this,conversationList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        mSpeechUtils.showListenVoiceDialog(this, new RobotRecognizerDialogListener(mSpeechUtils,conversationList,mAdapter,mListView,RobotActivity.this));
    }
}
