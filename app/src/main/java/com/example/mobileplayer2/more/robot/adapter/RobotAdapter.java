package com.example.mobileplayer2.more.robot.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.more.robot.bean.ConversationInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */

public class RobotAdapter extends BaseAdapter {
    private Context context;
    private List<ConversationInfo> conversationList;

    public RobotAdapter(Context context, List<ConversationInfo> conversationList) {
        this.context = context;
        this.conversationList = conversationList;
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null) {
            view = View.inflate(context, R.layout.listview_item, null);
        } else {
            view = convertView;
        }

        View answerView = view.findViewById(R.id.ll_answer);
        TextView answerText = (TextView) view.findViewById(R.id.tv_answer_text);
        ImageView answerImage = (ImageView) view.findViewById(R.id.iv_answer_image);
        TextView askerText = (TextView) view.findViewById(R.id.tv_asker_text);

        ConversationInfo info = conversationList.get(position);
        if(info.isAsker()) {
            // 当前是提问者, 隐藏回答者布局
            answerView.setVisibility(View.GONE);
            askerText.setVisibility(View.VISIBLE);
            askerText.setText(info.getAskerText());
        } else {
            // 当前是回答者, 隐藏提问者布局
            answerView.setVisibility(View.VISIBLE);
            askerText.setVisibility(View.GONE);

            answerText.setText(info.getAnswerText());
            if(info.getImageID() == -1) {
                answerImage.setVisibility(View.GONE);
            } else {
                answerImage.setVisibility(View.VISIBLE);
                answerImage.setImageResource(info.getImageID());
            }
        }
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
