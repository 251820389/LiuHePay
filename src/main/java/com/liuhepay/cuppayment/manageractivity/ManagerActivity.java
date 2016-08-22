package com.liuhepay.cuppayment.manageractivity;

import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.adapter.GeneralImageAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;

/**
 * Created by LEO on 2016/8/19.
 */
public class ManagerActivity extends BaseActivity {

    private GeneralImageAdapter adapter;
    private ListView listView;
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.listview);
        setTitleText(R.string.manager_activity_title);
        adapter = new GeneralImageAdapter(mContext, R.layout.gridview_item, "icon_sel", true);
        adapter.addAll((Object[]) getResources().getStringArray(R.array.array_manager_activity));
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptyLayout));
        listView.addFooterView(new ViewStub(this));
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
