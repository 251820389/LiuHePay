package com.liuhepay.cuppayment.sysactivity;

import java.util.Collections;

import android.content.Intent;
import android.widget.ListView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.adapter.GeneralSwitchAdapter;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.util.SharedPreferencesInfo;

/**
 * 屏蔽交易设置
 */
public class SysShieldTransactionAct extends BaseActivity {
    private static final int TRADITIONAL_TRADING = 0;
    private static final int ELEC_CASH_TRADE = 1;
    private static final int ELEC_WALLET_TRADE = 2;
    private static final int BYSTAGES_TRADE = 3;
    private static final int SCORE_TRADE = 4;
    private static final int PHONE_CHIP_TRADE = 5;
    private static final int PREPARED_TRADE = 6;
    private static final int ORDER_TRADE = 7;
    private static final int OTHERS_TRADE = 8;
    private static final int TRADE_INPUT_PWD = 9;
    private static final int TRADE_SWIPE_CARD = 10;
    private static final int AUTO_SIGNOUT = 11;
    private static final int SIGNATURE = 12;
    private boolean default_flag;
    private ListView listview = null;
    private GeneralSwitchAdapter adpter;
    private int position = 0;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.listview);
        Intent intent = getIntent();
        if (intent != null) {
            position = getIntent().getIntExtra("position", 0);
            String title = getIntent().getStringExtra("title");
            setTitleText(title);
        } else {
            setTitleText(getString(R.string.trade_switch_control_title));
        }
        listview = (ListView) findViewById(R.id.listview);
        initData();
    }

    protected void initData() {
        int id = 0;
        switch (position) {
            case TRADITIONAL_TRADING:
                id = R.array.array_switch;
                default_flag = true;
                break;
            case ELEC_CASH_TRADE:
                id = R.array.array_switch_elec;
                break;
            case ELEC_WALLET_TRADE:
                id = R.array.array_switch_elec_wallet;
                break;
            case BYSTAGES_TRADE:
                id = R.array.array_switch_bystages;
                break;
            case SCORE_TRADE:
                id = R.array.array_switch_score;
                break;
            case PHONE_CHIP_TRADE:
                id = R.array.array_switch_phone_chip;
                break;
            case PREPARED_TRADE:
                id = R.array.array_switch_prepared;
                break;
            case ORDER_TRADE:
                id = R.array.array_switch_order;
                break;
            case OTHERS_TRADE:
                id = R.array.array_switch_other_trade;
                break;
            case TRADE_INPUT_PWD:
                id = R.array.array_switch_input_pwd;
                break;
            case TRADE_SWIPE_CARD:
                id = R.array.array_switch_swipe_card;
                break;
            case AUTO_SIGNOUT:
                id = R.array.array_switch_settle;
                break;
            case SIGNATURE:
                id = R.array.array_switch_signature;
                break;
            default:
                break;
        }
        SharedPreferencesInfo.switch_arrlist.clear();
        Collections.addAll(SharedPreferencesInfo.switch_arrlist, (Object[]) getResources().getStringArray(id));
        adpter = new GeneralSwitchAdapter(mContext, R.layout.item_array_listview_switch_item, sharedInfo, default_flag);
        adpter.addAll((Object[]) getResources().getStringArray(id));
        listview.setAdapter(adpter);
    }
}
