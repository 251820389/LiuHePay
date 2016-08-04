package com.liuhepay.cuppayment;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.ToastUtil;

public class ElecCashMainActivity extends BaseActivity {

	private ListView listView;
	private GeneralImageAdapter adapter;

	@Override
	protected void initView() {
		super.initView();
		addContentView(R.layout.listview);
		setTitleText(R.string.elec_cash_main_activity);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new GeneralImageAdapter(mContext, R.layout.listview_item,
				"icon_small", false);
		adapter.addAll((Object[]) getResources().getStringArray(
				R.array.array_elec_cash_main_activity));
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.emptyLayout));
		listView.addFooterView(new ViewStub(this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				ToastUtil.showToast(mContext, R.string.not_support);
				SystemClock.sleep(300);
				backMainScreen(OPERATOR);
			}
		});
		
	}
}
