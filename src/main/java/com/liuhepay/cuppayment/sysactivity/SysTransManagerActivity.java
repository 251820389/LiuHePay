package com.liuhepay.cuppayment.sysactivity;

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
                switch (position) {
                    case TRANS_SWITCH:
                        ScreenSwitch.switchActivity(mContext, SysTransSwitchActivity.class);
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
