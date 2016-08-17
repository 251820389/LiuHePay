package com.liuhepay.cuppayment.sysactivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.bean.OthersInfoBean;
import com.liuhepay.cuppayment.util.ToastUtil;

/**
 * Created by LEO on 2016/8/11.
 */
public class SysOthersActivity extends BaseActivity{
    private Switch				input_manager_pwd, input_card_num, default_swipe_trade;
    private EditText			max_quota;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.sys_others_layout);
        setTitleText(getString(R.string.sys_others_trans_title));

        input_manager_pwd = (Switch) findViewById(R.id.others_manager_pwd);
        input_card_num = (Switch) findViewById(R.id.others_input_card_num);
        default_swipe_trade = (Switch) findViewById(R.id.others_default_swipe_trade);
        max_quota = (EditText) findViewById(R.id.other_max_quota);

        OthersInfoBean otherBean = sharedInfo.getOthersInfo();
        input_manager_pwd.setChecked(otherBean.isOthers_manager_pwd());
        input_card_num.setChecked(otherBean.isAllow_input_cardnum());
        default_swipe_trade.setChecked(otherBean.isDefault_swipe_card_type());
        max_quota.setText(otherBean.getRefund_max_quota());
    }

    public void onClick(View view)
    {
        if (max_quota.getText().toString().length() <= 0)
        {
            ToastUtil.showToast(mContext, getString(R.string.error_refund_max_quota_not_allow_null));
            return;
        }
        OthersInfoBean infoBean = new OthersInfoBean();
        infoBean.setOthers_manager_pwd(input_manager_pwd.isChecked());
        infoBean.setAllow_input_cardnum(input_card_num.isChecked());
        infoBean.setDefault_swipe_card_type(default_swipe_trade.isChecked());
        infoBean.setRefund_max_quota(max_quota.getText().toString());
        sharedInfo.setOthersInfo(infoBean);
        ToastUtil.showToast(mContext, getString(R.string.save_args_success));
        finish();
    }
}
