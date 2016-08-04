package com.liuhepay.cuppayment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.pay.AlipayRequestParameter;
import com.liuhepay.cuppayment.pay.WxRequestParameter;
import com.liuhepay.cuppayment.payinterface.OnClickInterface;
import com.liuhepay.cuppayment.util.ConfigUtil;
import com.liuhepay.cuppayment.util.DialogUtil;
import com.liuhepay.cuppayment.util.GetQrCode;
import com.liuhepay.cuppayment.util.ToastUtil;
import com.zxing.activity.CaptureActivity;

public class InputAmountActivity extends BaseActivity{

    private int type = 0;
    private EditText input_amount;
    private String moneyNum = "0.00";
    private String wxMoneyNum = "0";
    private String authCode = "";
    private LinearLayout ll_amount;
    private ImageView qr_code;

    @Override
    protected void initView() {
        super.initView();
        addContentView(R.layout.amount_input_layout);
        setTitleText(R.string.trade_amount);
        type = getIntent().getIntExtra("type", ConfigUtil.WXSCANPAY);
        input_amount = (EditText) findViewById(R.id.amount);
        ll_amount = (LinearLayout) findViewById(R.id.ll_amount);
        qr_code = (ImageView) findViewById(R.id.qr_code);
        setPricePoint(input_amount);
    }

    public void onClick(View view) {
        moneyNum = input_amount.getText().toString();
        moneyNum = ConfigUtil.covertPoint(moneyNum);
        if (moneyNum.length() == 3) {
            moneyNum = moneyNum + "0";
        }
        if (moneyNum == null || moneyNum.equals("") || moneyNum.equals("0.0") || moneyNum.equals("0.00")) {
            ToastUtil.showToast(mContext, R.string.input_trade_amount);
            return;
        }
        wxMoneyNum = Double.parseDouble(moneyNum) * 100 + "";
        wxMoneyNum = wxMoneyNum.substring(0, wxMoneyNum.indexOf("."));
        Intent intent = null;
        switch (type) {
            case ConfigUtil.WXSCANPAY:
                // 微信扫码支付
                WxRequestParameter wxRequest = new WxRequestParameter();
                wxRequest.payByWxScan(mHandler, wxMoneyNum);
                break;
            case ConfigUtil.WXPAY:
                // 微信条码支付
                intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, 0);
                break;
            case ConfigUtil.ALIPAYSCANPAY:
                // 支付宝扫码支付
                AlipayRequestParameter alipayRequest = new AlipayRequestParameter();
                alipayRequest.payByAlipayScan(moneyNum, mHandler);
                break;
            case ConfigUtil.ALIPAYPAY:
                // 支付宝条码支付
                intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case ConfigUtil.SALE:
                //TODO 调用检卡流程
                showPwdDialog();
                break;
            case ConfigUtil.PREAUTH:
                showPwdDialog();
                break;
            default:
                break;
        }

    }
    //input password
    private void showPwdDialog(){
        DialogUtil dialog = new DialogUtil();
        dialog.pwdDialog(mContext, moneyNum, new OnClickInterface() {
            @Override
            public void onClickListener() {
                //TODO 提交给底层处理
                ToastUtil.showToast(mContext,"密码提交成功");
            }

            @Override
            public void onDismissListener() {
                //TODO 返回主界面释放资源
                ToastUtil.showToast(mContext, mContext.getString(R.string.close_trade));
                InputAmountActivity.this.finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "requestCode = " + requestCode);
        Log.e(TAG, "resultCode = " + resultCode);
        // 扫码结果处理
        if (resultCode != RESULT_OK) {
            ToastUtil.showToast(mContext, R.string.error_scan_pay);
            return;
        }
        // 处理扫描结果（在界面上显示）
        Bundle bundle = data.getExtras();
        authCode = bundle.getString("result");
        switch (requestCode) {
            case 0:
                WxRequestParameter wxRequest = new WxRequestParameter();
                wxRequest.payByWx(authCode, mHandler, wxMoneyNum);
                break;
            case 1:
                AlipayRequestParameter alipayRequest = new AlipayRequestParameter();
                alipayRequest.payByAlipay(authCode, mHandler, moneyNum);
                break;
            default:
                break;
        }
    }

    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

    private JSONObject json = null;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:// 支付宝条码支付
                    json = JSONObject.parseObject(msg.getData().getString("result")).getJSONObject("alipay_trade_pay_response");
                    // TODO show dialog支付宝条码支付结果展示
                    if (json.getString("code").equals("10000")) {
                        ToastUtil.showToast(mContext, R.string.pay_success);
                    } else {
                        ToastUtil.showToast(mContext, R.string.pay_failed);
                    }

                    break;
                case 1:// 支付宝扫码支付
                    String qrcodeAlipay = msg.getData().getString("qrcode");
                    json = JSON.parseObject(qrcodeAlipay).getJSONObject("alipay_trade_precreate_response");
                    // 处理qrcode xml
                    if (json.getString("code").equals("10000")) {
                        qrcodeAlipay = json.getString("qr_code");
                        // ToastUtil.showToast(mContext, "支付成功");
                    } else {
                        String reason = json.getString("msg");
                        if (reason == null || "".equals(reason)) {
                            reason = getString(R.string.error_get_qrcode);
                        }
                        ToastUtil.showToast(mContext, reason);
                        setTitleText(R.string.scan_pay);
                        ll_amount.setVisibility(View.GONE);
                        qr_code.setBackground(getDrawable(R.drawable.logo));
                    }
                    setTitleText(R.string.scan_pay);
                    Bitmap bitmap = GetQrCode.createImage(qrcodeAlipay);
                    ll_amount.setVisibility(View.GONE);
                    qr_code.setImageBitmap(bitmap);

                    break;
                case 2:
                    int result = msg.getData().getInt("result"); // 0：失败 1：成功
                    Log.e("微信刷卡", String.valueOf(result));
                    if (result == 1) {
                        ToastUtil.showToast(mContext, R.string.pay_success);
                    } else {
                        ToastUtil.showToast(mContext, R.string.pay_failed);
                    }
                    break;
                case 3:
                    String qrcode = msg.getData().getString("qrcode");
                    // 处理qrcode xml
                    if (qrcode.indexOf("code_url") != -1) {
                        qrcode = qrcode.substring(qrcode.indexOf("<code_url>") + 19, qrcode.indexOf("</code_url>") - 3);
                    } else {
                        String reason = json.getString("return_msg");
                        if (reason == null || "".equals(reason)) {
                            reason = getString(R.string.error_get_qrcode);
                        }
                        Toast.makeText(getApplicationContext(), "获取二维码失败", Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(mContext, R.string.pay_success);
                        setTitleText(R.string.scan_pay);
                        ll_amount.setVisibility(View.GONE);
                        qr_code.setBackground(getDrawable(R.drawable.logo));
                    }

                    setTitleText(R.string.scan_pay);
                    Bitmap bitmapWx = GetQrCode.createImage(qrcode);
                    ll_amount.setVisibility(View.GONE);
                    qr_code.setImageBitmap(bitmapWx);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
