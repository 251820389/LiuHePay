package com.liuhepay.cuppayment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.alipay.Config;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ConfigUtil;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.ToastUtil;

public class PreAuthMainActivity extends BaseActivity {

    private ListView listView;
    private GeneralImageAdapter adapter;
    private final static int PREAUTH = 0;
    private final static int PREAUTHCOMPLETE = 1;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.listview);
        setTitleText(R.string.preauth_main_activity);
        listView = (ListView) findViewById(R.id.listview);
        adapter = new GeneralImageAdapter(mContext, R.layout.listview_item, "icon_small", false);
        adapter.addAll((Object[]) getResources().getStringArray(R.array.array_preauth_main_activity));
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyLayout));
        listView.addFooterView(new ViewStub(this));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case PREAUTH:
                        bundle.putInt("type", ConfigUtil.PREAUTH);
                        ScreenSwitch.switchActivity(mContext,InputAmountActivity.class,bundle);
                        break;
                    case PREAUTHCOMPLETE:
                        bundle.putInt("type", ConfigUtil.PREAUTHCOMPLETE);
                        ScreenSwitch.switchActivity(mContext,InputAmountActivity.class,bundle);
                        break;
                    default:
                        ToastUtil.showToast(mContext, R.string.not_support);
                        SystemClock.sleep(300);
                        backMainScreen(OPERATOR);
                        break;
                }
            }
        });

    }
}
