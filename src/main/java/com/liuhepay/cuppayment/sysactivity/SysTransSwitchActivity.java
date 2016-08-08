package com.liuhepay.cuppayment.sysactivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.ToastUtil;

public class SysTransSwitchActivity extends BaseActivity {

	private ListView listView;
	private GeneralImageAdapter adapter;

	@Override
	protected void initView() {
		super.initView();
		addContentView(R.layout.listview);
		setTitleText(getString(R.string.sys_trans_switch_activity));
		listView = (ListView) findViewById(R.id.listview);
		adapter = new GeneralImageAdapter(mContext, R.layout.listview_item,
				"icon_small", false);
		adapter.addAll((Object[]) getResources().getStringArray(
				R.array.array_system_trans_switch_activity));
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.emptyLayout));
		listView.addFooterView(new ViewStub(this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				bundle.putString("title", getResources().getStringArray(R.array.array_system_trans_switch_activity)[position]);
				ScreenSwitch.switchActivity(mContext, SysShieldTransactionAct.class, bundle);
			}
		});
		
	}
}
