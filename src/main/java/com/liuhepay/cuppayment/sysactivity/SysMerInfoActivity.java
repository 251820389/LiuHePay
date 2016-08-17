package com.liuhepay.cuppayment.sysactivity;

import android.view.View;
import android.widget.EditText;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.bean.MerInfoBean;
import com.liuhepay.cuppayment.util.ToastUtil;

/**
 * Created by LEO on 2016/8/8.
 */
public class SysMerInfoActivity extends BaseActivity {

    private EditText midEt, tidEt, firmIdEt;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.sys_mer_info_activity);
        setTitleText(R.string.sys_mer_info_activity_title);
        midEt = (EditText) findViewById(R.id.mid_text);
        tidEt = (EditText) findViewById(R.id.tid_text);
        firmIdEt = (EditText) findViewById(R.id.firm_text);
        MerInfoBean merInfo = sharedInfo.getMerInfo();
        midEt.setText(merInfo.getMid());
        tidEt.setText(merInfo.getTid());
        firmIdEt.setText(merInfo.getFirmid());
    }

    public void onClick(View view) {
        MerInfoBean merInfo = new MerInfoBean();
        String mid = midEt.getText().toString();
        if (mid.equals("")) {
            ToastUtil.showToast(mContext, getString(R.string.error_mid_not_allow_null));
            return;
        }
        String tid = tidEt.getText().toString();
        if (tid.equals("")) {
            ToastUtil.showToast(mContext, getString(R.string.error_tid_not_allow_null));
            return;
        }
        String firmId = firmIdEt.getText().toString();
        if (firmId.equals("")) {
            ToastUtil.showToast(mContext, getString(R.string.error_tid_not_allow_null));
            return;
        }
        merInfo.setMid(mid);
        merInfo.setTid(tid);
        merInfo.setFirmid(firmId);
        sharedInfo.setMerInfo(merInfo);
        ToastUtil.showToast(mContext, getString(R.string.save_args_success));
        finish();
    }

}
