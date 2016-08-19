package com.liuhepay.cuppayment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ConfigUtil;
import com.liuhepay.cuppayment.util.MyLog;
import com.liuhepay.cuppayment.util.ScreenSwitch;

public class OperatorMainActivity extends BaseActivity {
    private static final int WXSACN = 0;
    private static final int WXPAY = 1;
    private static final int ALIPAYSACN = 2;
    private static final int ALIPAY = 3;
    private static final int SALE = 4;
    private static final int REFUND = 5;
    private static final int VOID = 6;
    private static final int PREAUTH = 7;
    private static final int PRINT = 8;
    private static final int MANAGER = 10;
    private static final int OTHERS = 11;
    private static final int UPDATETEXT = 1001;
    private GridView gridView;
    private GeneralImageAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.gridview);
        setTitleText(R.string.function_selection);
        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new GeneralImageAdapter(mContext, R.layout.gridview_item, "icon_sel", true);
        adapter.addAll((Object[]) getResources().getStringArray(R.array.array_operator_main_activity));
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case WXSACN:
                    case WXPAY:
                    case ALIPAYSACN:
                    case ALIPAY:
                        bundle.putInt("type", position);
                        ScreenSwitch.switchActivity(mContext, InputAmountActivity.class, bundle);
                        break;
                    case SALE:
                        bundle.putInt("type", ConfigUtil.SALE);
                        ScreenSwitch.switchActivity(mContext, InputAmountActivity.class, bundle);
                        break;
                    case REFUND:
                        break;
                    case VOID:
                        break;
                    case PREAUTH:
                        ScreenSwitch.switchActivity(mContext, PreAuthMainActivity.class);
                        break;
                    case PRINT:
                        ScreenSwitch.switchActivity(mContext, PrintMainActivity.class);
                        break;
                    case MANAGER:
                        ScreenSwitch.switchActivity(mContext, ManagerMainActivity.class);
                        break;
                    case OTHERS:
                        ScreenSwitch.switchActivity(mContext, OthersMainActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        type = OPERATOR;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getRunningActivityName();
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATETEXT:
                    rightBtn.setText((String) msg.obj);
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", msg.arg1);
                    ScreenSwitch.switchActivity(mContext, InputAmountActivity.class, bundle);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        type_exit = true;
        return super.onKeyDown(keyCode, event);
    }

}
