package com.example.mobileplayer2.more.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobileplayer2.R;
import com.example.mobileplayer2.more.channel.activity.ChannelActivity;
import com.example.mobileplayer2.adapter.ModelRecyclerAdapter;
import com.example.mobileplayer2.adapter.RecyclerItemViewId;
import com.example.mobileplayer2.more.bean.MoreBean;
import com.example.mobileplayer2.utils.Config;
import com.example.mobileplayer2.view.AwesomeFontTextView;

/**
 * Created by Administrator on 2017/7/27.
 */
@RecyclerItemViewId(R.layout.more_item)
public class MoreHolder extends ModelRecyclerAdapter.ModelViewHolder implements View.OnClickListener ,Config{
    private AwesomeFontTextView mAwesomeFontTextView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private int mPosition;
    private Context mContext;

    public MoreHolder(View itemView) {
        super(itemView);
        mAwesomeFontTextView = (AwesomeFontTextView) itemView.findViewById(R.id.tv_font);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_more);
        mLinearLayout.setOnClickListener(this);
    }

    @Override
    public void convert(Object item, ModelRecyclerAdapter adapter, Context context, int position) {
        MoreBean moreBean = (MoreBean) item;
        mContext = context;
        mPosition = position;
        if(position % 2 == 0){
            mLinearLayout.setBackgroundResource(R.color.more_bg_color_1);
            mAwesomeFontTextView.setTextColor(ContextCompat.getColor(context,R.color.more_tv_color_1));
            mTextView.setTextColor(ContextCompat.getColor(context,R.color.more_tv_color_1));
        }else {
            mLinearLayout.setBackgroundResource(R.color.more_bg_color_2);
            mAwesomeFontTextView.setTextColor(ContextCompat.getColor(context,R.color.more_tv_color_2));
            mTextView.setTextColor(ContextCompat.getColor(context,R.color.more_tv_color_2));
        }
        mAwesomeFontTextView.setText(moreBean.getFont());
        mTextView.setText(moreBean.getName());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        switch (mPosition){
            case 0:
                intent.setClassName(mContext,"com.example.mobileplayer2.more.channel.activity.ChannelActivity");
                intent.putExtra(EXTRA_CALLING_TYPE, CALLING_TYPE_VIDEO);
                intent.putExtra(EXTRA_VENDOR_KEY, EXTRA_VENDOR_KEY_V);
                intent.putExtra(EXTRA_CHANNEL_ID, EXTRA_CHANNEL_ID_V);
                break;

            case 1:
                intent.setClassName(mContext,"com.example.mobileplayer2.more.robot.activity.RobotActivity");
                break;
        }
        mContext.startActivity(intent);
    }
}
