package com.liuhepay.cuppayment.sysactivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.bean.OfflineInfoBean;
import com.liuhepay.cuppayment.util.ToastUtil;


public class SysOfflineControlActivity extends BaseActivity {
    private Switch offline_send_way;
    private EditText offline_send_count, offline_auto_send_count;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.sys_offline_trans_layout);
        setTitleText(getString(R.string.sys_offline_trans_title));
        offline_send_way = (Switch) findViewById(R.id.offline_send_way);
        offline_send_count = (EditText) findViewById(R.id.offline_send_count);
        offline_auto_send_count = (EditText) findViewById(R.id.offline_auto_send_count);
        initData();
    }

    protected void initData() {
        OfflineInfoBean infoBean = sharedInfo.getOfflineInfo();
        offline_send_way.setChecked(infoBean.getOfflineSendWay());
        offline_send_count.setText(infoBean.getOfflineSendCount());
        offline_auto_send_count.setText(infoBean.getOfflineAutoSendCount());
    }

    public void onClick(View view) {
        OfflineInfoBean infoBean = new OfflineInfoBean();
        String count = offline_send_count.getText().toString();
        String auto_count = offline_auto_send_count.getText().toString();
        if (count.length() <= 0) {
            ToastUtil.showToast(mContext, getString(R.string.error_offline_send_count_not_allow_null));
            return;
        }
        if (auto_count.length() <= 0) {
            ToastUtil.showToast(mContext, getString(R.string.error_auto_send_count_not_allow_null));
            return;
        }
        infoBean.setOfflineSendCount(count);
        infoBean.setOfflineAutoSendCount(auto_count);
        infoBean.setOfflineSendWay(offline_send_way.isChecked());
        sharedInfo.setOfflineInfo(infoBean);
        ToastUtil.showToast(mContext, getString(R.string.save_args_success));
        finish();
    }
}
