package com.liuhepay.cuppayment;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.ToastUtil;

public class ManagerMainActivity extends BaseActivity {

	private static final int SIGN = 0;
	private static final int QUERY_REANSACTION = 2;
	private static final int OPERATOR_MANAGER = 3;
	private ListView listView;
	private GeneralImageAdapter adapter;

	@Override
	protected void initView() {
		super.initView();
		addContentView(R.layout.listview);
		setTitleText(R.string.manager_main_activity);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new GeneralImageAdapter(mContext, R.layout.listview_item,
				"icon_small", false);
		adapter.addAll((Object[]) getResources().getStringArray(
				R.array.array_manager_main_activity));
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.emptyLayout));
		listView.addFooterView(new ViewStub(this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				switch (position) {
				case SIGN:
					ScreenSwitch.switchActivity(mContext, SignActivity.class);
					break;
				case QUERY_REANSACTION:
					ScreenSwitch.switchActivity(mContext, HistoryQueryActivity.class);
					break;
				case OPERATOR_MANAGER:
					ScreenSwitch.switchActivity(mContext, OperatorManagerActivity.class);
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
