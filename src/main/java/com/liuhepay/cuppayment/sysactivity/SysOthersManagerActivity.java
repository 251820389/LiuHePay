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

public class SysOthersManagerActivity extends BaseActivity {

	private static final int CLEAR = 0;
	private static final int DOWNLOAD = 2;
	private static final int TEST = 5;

	private ListView listView;
	private GeneralImageAdapter adapter;

	@Override
	protected void initView() {
		super.initView();
		addContentView(R.layout.listview);
		setTitleText(R.string.system_other_manager_activity);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new GeneralImageAdapter(mContext, R.layout.listview_item,
				"icon_small", false);
		adapter.addAll((Object[]) getResources().getStringArray(
				R.array.array_system_other_manager_activity));
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.emptyLayout));
		listView.addFooterView(new ViewStub(this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				switch (position) {
				case CLEAR:
					break;
				case DOWNLOAD:
					ScreenSwitch.switchActivity(mContext, SysDownloadManagerActivity.class);
					break;
				case TEST:
					ScreenSwitch.switchActivity(mContext, SysTestManagerActivity.class);
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
