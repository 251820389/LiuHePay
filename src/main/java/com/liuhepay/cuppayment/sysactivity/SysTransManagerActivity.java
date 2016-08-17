package com.liuhepay.cuppayment.sysactivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.ToastUtil;

public class SysTransManagerActivity extends BaseActivity {

    private ListView listView;
    private GeneralImageAdapter adapter;
    private static final int TRANS_SWITCH = 0;
    private static final int INPUT_PWD = 1;
    private static final int SWIPE_CARD = 2;
    private static final int SETTLE_TRADE = 3;
    private static final int OFFLINE = 4;
    private static final int OTHERS = 5;
    private static final int SIGNATURE = 6;

    @Override
    protected void initView() {
        super.initView();
        type = NORMAL;
        addContentView(R.layout.listview);
        setTitleText(R.string.system_trans_manager_activity);
        listView = (ListView) findViewById(R.id.listview);
        adapter = new GeneralImageAdapter(mContext, R.layout.listview_item,
                "icon_small", false);
        adapter.addAll((Object[]) getResources().getStringArray(
                R.array.array_system_trans_manager_activity));
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyLayout));
        listView.addFooterView(new ViewStub(this));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case TRANS_SWITCH:
                        ScreenSwitch.switchActivity(mContext, SysTransSwitchActivity.class);
                        break;
                    case INPUT_PWD://交易输密控制
                        bundle.putInt("position", 9);
                        bundle.putString("title", getString(R.string.sys_trans_input_pwd_title));
                        ScreenSwitch.switchActivity(mContext, SysShieldTransactionAct.class, bundle);
                        break;
                    case SWIPE_CARD://交易刷卡控制
                        bundle = new Bundle();
                        bundle.putInt("position", 10);
                        bundle.putString("title", getString(R.string.sys_trans_swipe_card_title));
                        ScreenSwitch.switchActivity(mContext, SysShieldTransactionAct.class, bundle);
                        break;
                    case SETTLE_TRADE://结算交易控制
                        bundle = new Bundle();
                        bundle.putInt("position", 11);
                        bundle.putString("title", getString(R.string.sys_settle_trans_title));
                        ScreenSwitch.switchActivity(mContext, SysShieldTransactionAct.class, bundle);
                        break;
                    case OFFLINE://离线交易控制
                        ScreenSwitch.switchActivity(mContext, SysOfflineControlActivity.class);
                        break;
                    case OTHERS://其他交易控制
                        ScreenSwitch.switchActivity(mContext, SysOthersActivity.class);
                        break;
                    case SIGNATURE://电子签名控制
                        bundle = new Bundle();
                        bundle.putInt("position", 12);
                        bundle.putString("title", getString(R.string.sys_signature_trans_title));
                        ScreenSwitch.switchActivity(mContext, SysShieldTransactionAct.class, bundle);
                        break;
                    default:
                        ToastUtil.showToast(mContext, R.string.not_support);
                        SystemClock.sleep(300);
                        backMainScreen(SYSTEM);
                        break;
                }
            }
        });

    }
}
