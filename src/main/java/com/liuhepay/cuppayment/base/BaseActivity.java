package com.liuhepay.cuppayment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhepay.cuppayment.OperatorMainActivity;
import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.sysactivity.SysMainActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.SharedPreferencesInfo;
import com.liuhepay.cuppayment.util.ToastUtil;

public class BaseActivity extends Activity {

    protected static final String TAG = "LiuhePay";
    protected TextView leftBtn, titleText, rightBtn;
    private RelativeLayout titleBar;
    protected Context mContext;
    protected static final int OPERATOR = 0;
    protected static final int MANAGER = 1;
    protected static final int SYSTEM = 2;
    protected static final int EXIT = 3;
    protected static final int NORMAL = 4;
    protected static int type = 0;
    protected boolean type_exit = false;
    private long mExitTime;
    protected SharedPreferencesInfo sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.base_activity_layout);
        mContext = this;
        getRunningActivityName();
        sharedInfo = SharedPreferencesInfo.getInstance(mContext);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 返回事件
            backMainScreen(type);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void backMainScreen(int type) {
        if (type_exit) {
            type = EXIT;
        }
        switch (type) {
            case OPERATOR:
                ScreenSwitch.switchActivity(mContext, OperatorMainActivity.class);
                break;
            case MANAGER:
                // ScreenSwitch.switchActivity(mContext, OperatorMainActivity.class);
                break;
            case SYSTEM:
                ScreenSwitch.switchActivity(mContext, SysMainActivity.class);
                break;
            case NORMAL:
                finish();
                break;
            case EXIT:
                // 按2次退出应用
                if ((System.currentTimeMillis() - mExitTime) > 800) {
                    ToastUtil.showToast(mContext, R.string.exit);
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    /**
     * add view
     *
     * @param layoutId
     */
    protected View addContentView(int layoutId) {
        LinearLayout llayout = (LinearLayout) findViewById(R.id.index_view);
        View view = View.inflate(mContext, layoutId, null);
        llayout.addView(view);
        return llayout;
    }

    /**
     * add view
     *
     * @param view
     */
    protected void addContentView(View view) {
        LinearLayout llayout = (LinearLayout) findViewById(R.id.index_view);
        llayout.addView(view);
    }

    /**
     * you need to add this:super.initView();
     */
    protected void initView() {
        titleBar = (RelativeLayout) findViewById(R.id.titleBar);
        leftBtn = (TextView) findViewById(R.id.leftBtn);
        titleText = (TextView) findViewById(R.id.titleText);
        rightBtn = (TextView) findViewById(R.id.rightBtn);
    }

    protected void setTitleText(int titleTextId) {
        titleText.setText(getString(titleTextId));
    }

    protected void setTitleText(String title) {
        titleText.setText(title);
    }

    /**
     * set leftBtnText content
     *
     * @param leftTextId
     */
    protected void setleftBtnText(int leftTextId) {
        leftBtn.setText(getString(leftTextId));
    }

    /**
     * set leftBtnText content
     *
     * @param leftText
     */
    protected void setleftBtnText(String leftText) {
        leftBtn.setText(leftText);
    }

    /**
     * set rightBtnTextID content
     *
     * @param rightTextId
     */
    protected void setRightBtnText(int rightTextId) {
        rightBtn.setText(getString(rightTextId));
    }

    /**
     * set rightBtnText content
     *
     * @param rightText
     */
    protected void setRightBtnText(String rightText) {
        rightBtn.setText(rightText);
    }

    /**
     * set title content
     *
     * @param leftTextId
     * @param titleTextId
     * @param rightTextId
     */
    protected void setTitleText(int leftTextId, int titleTextId, int rightTextId) {
        leftBtn.setText(getString(leftTextId));
        titleText.setText(getString(titleTextId));
        rightBtn.setText(getString(rightTextId));
    }

    /**
     * set title content
     *
     * @param leftBtnText
     * @param title
     * @param rightBtnText
     */
    protected void setTitleText(String leftBtnText, String title,
                                String rightBtnText) {
        leftBtn.setText(leftBtnText);
        titleText.setText(title);
        rightBtn.setText(rightBtnText);
    }

    /**
     * hide titlebar
     */
    protected void hideTitleBar() {
        if (titleBar == null) {
            ToastUtil.showToast(mContext, R.string.error_tip);
            return;
        }
        titleBar.setVisibility(View.GONE);
    }

    /**
     * hide or show leftBtn
     *
     * @param hide true:hide false:show
     */
    protected void hideOrShowLeftBtn(Boolean hide) {
        if (leftBtn == null) {
            ToastUtil.showToast(mContext, R.string.error_tip);
            return;
        }
        if (hide) {
            leftBtn.setVisibility(View.GONE);
        } else {
            leftBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * hide or show rightBtn
     *
     * @param hide true:hide false:show
     */
    protected void hideOrShowRightBtn(Boolean hide) {
        if (rightBtn == null) {
            ToastUtil.showToast(mContext, R.string.error_tip);
            return;
        }
        if (hide) {
            rightBtn.setVisibility(View.GONE);
        } else {
            rightBtn.setVisibility(View.VISIBLE);
        }
    }

    protected String getRunningActivityName() {
        String contextString = mContext.toString();
        String activity_name = contextString.substring(
                contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
        Log.i(TAG, "this current activity's name :" + activity_name);
        return activity_name;
    }
}
