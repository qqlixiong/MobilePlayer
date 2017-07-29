package com.example.mobileplayer2.more.robot.listener;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobileplayer2.more.robot.adapter.RobotAdapter;
import com.example.mobileplayer2.more.robot.bean.ConversationInfo;
import com.example.mobileplayer2.more.robot.utils.ResouesUtils;
import com.example.mobileplayer2.more.robot.utils.SpeechUtils;
import com.example.mobileplayer2.more.robot.utils.UIUtils;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/29.
 */

public class RobotRecognizerDialogListener implements RecognizerDialogListener {

    private StringBuffer sb = new StringBuffer();
    private SpeechUtils mSpeechUtils;
    private List<ConversationInfo> conversationList;
    private RobotAdapter mAdapter;
    private ListView mListView;
    private Activity activity;

    public RobotRecognizerDialogListener(SpeechUtils speechUtils, List<ConversationInfo> conversationList, RobotAdapter adapter, ListView listView, Activity activity) {
        mSpeechUtils = speechUtils;
        this.conversationList = conversationList;
        mAdapter = adapter;
        mListView = listView;
        this.activity = activity;
    }

    @Override
    public void onResult(RecognizerResult recognizerResult, boolean isLast) {

        //语音识别过来的内容
        String resultString = recognizerResult.getResultString();
        sb.append(UIUtils.getVoice(resultString));//添加成一整句

        System.out.println("正在识别中: " + sb.toString());
        String askerText = "你说什么，我没听见...";
        String answer;
        int imageID = -1;
        if (isLast) { // true, 所有的数据都解析完闭. 赋值给askerText
            askerText = sb.toString();
            answer = sb.toString();
            if (askerText.contains("美女")) {
                Random random = new Random();
                int index = random.nextInt(3);
                answer = ResouesUtils.mmTextArray[index];
                imageID = ResouesUtils.mmImageArray[index];
            } else if (askerText.contains("精忠报国")) {
                answer = ResouesUtils.markTextArray[0];
                imageID = ResouesUtils.markImageArray[0];
            }else if (askerText.contains("奶茶妹")) {
                answer = ResouesUtils.markTextArray[2];
                imageID = ResouesUtils.mmImageArray[3];
            }else if (askerText.contains("开会")) {
                answer = ResouesUtils.markTextArray[1];
                imageID = ResouesUtils.markImageArray[1];
            } else if (askerText.contains("报警")) {
                String phoneNumber = "110";
                Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if(isGrantCallPhone())activity.startActivity(intentPhone);
                return;
            }else if(askerText.contains("120")) {
                String phoneNumber = "120";
                Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if(isGrantCallPhone())activity.startActivity(intentPhone);
                return;
            } else if (askerText.contains("打开微信")) {
                Intent intent = new Intent();
                ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                activity.startActivity(intent);
                return;
            } else if (askerText.contains("拍照")) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用android自带的照相机
//                   photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                activity.startActivity(intent);
                return;
            }else if(askerText.contains("名字")) {
                answer = ResouesUtils.mmTextArray[3];
            }else if(askerText.contains("我在哪儿")) {
                answer = ResouesUtils.mmTextArray[4];
            }else if(askerText.contains("班长是谁")) {
                answer = ResouesUtils.mmTextArray[5];
            }else if(askerText.contains("老师是谁")) {
                answer = ResouesUtils.mmTextArray[6];
                imageID = ResouesUtils.mmImageArray[3];
            }else if(askerText.contains("你会做什么")) {
                answer = ResouesUtils.mmTextArray[7];
            }else if(askerText.contains("爱你")) {
                answer = ResouesUtils.mmTextArray[8];
            }
            sb = new StringBuffer();
        } else { // 还没有把数据解析完毕, return回去, 不去执行后面的代码, 继续下一次拼接.
            return;
        }

        ConversationInfo info = new ConversationInfo(askerText, null, -1, true);
        conversationList.add(info);
        mAdapter.notifyDataSetChanged();

        // 准备回答的数据.
        info = new ConversationInfo(null, answer, imageID, false);
        conversationList.add(info);

        mAdapter.notifyDataSetChanged();
        mListView.setSelection(conversationList.size());
        // 把answer说出来
        mSpeechUtils.speakText(activity, answer);
    }

    @Override
    public void onError(SpeechError speechError) {
        Toast.makeText(activity, "识别出错了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 解决安卓6.0以上版本动态获取直接打电话的权限
     * @return
     */
    public boolean isGrantCallPhone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.CALL_PHONE
            }, 1);

            return false;
        }

        return true;
    }
}
