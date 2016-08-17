package com.liuhepay.cuppayment.sysactivity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;

public class SysMainActivity extends BaseActivity {
    private static final int MER_INFO = 0;
    private static final int TRANS_MANAGER = 1;
    private static final int COMMUNICATE_MANAGER = 3;
    private static final int MTK_MANAGER = 4;
    private static final int KEY_MANAGER = 5;
    private static final int OTHER_MANAGER = 6;
    private GridView gridView;
    private GeneralImageAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.gridview);
        setTitleText(R.string.system_manager_activity);
        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new GeneralImageAdapter(mContext, R.layout.gridview_item,
                "icon_big", true);
        adapter.addAll((Object[]) getResources().getStringArray(
                R.array.array_system_manager_activity));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
                switch (position) {
                    case MER_INFO:
                        ScreenSwitch.switchActivity(mContext, SysMerInfoActivity.class);
                        break;
                    case TRANS_MANAGER:
                        ScreenSwitch.switchActivity(mContext, SysTransManagerActivity.class);
                        break;
                    case COMMUNICATE_MANAGER:
                        ScreenSwitch.switchActivity(mContext, SysCommunicateManagerActivity.class);
                        break;
                    case MTK_MANAGER:
                        ScreenSwitch.switchActivity(mContext, SysTmkManagerActivity.class);
                        break;
                    case KEY_MANAGER:
                        ScreenSwitch.switchActivity(mContext, SysKeyManagerActivity.class);
                        break;
                    case OTHER_MANAGER:
                        ScreenSwitch.switchActivity(mContext, SysOthersManagerActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        type = SYSTEM;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getRunningActivityName();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        type_exit = true;
        return super.onKeyDown(keyCode, event);
    }
}
