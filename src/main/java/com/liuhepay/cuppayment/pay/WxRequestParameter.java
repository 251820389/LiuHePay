package com.liuhepay.cuppayment.pay;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.liuhepay.cuppayment.util.MD5;
import com.liuhepay.cuppayment.util.RandomStringGenerator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WxRequestParameter {

    private static final String TAG = "LiuhePay";
    /**
     * 微信条码支付
     *
     * @param auth_code,payResultHandler,moneyNum
     */
    public void payByWx(String auth_code, final Handler payResultHandler, String moneyNum) {
        final String url = "https://api.mch.weixin.qq.com/pay/micropay";
        final String reqXml = getRequestXmlByOrder(auth_code, moneyNum);
        Log.i(TAG, "WX Req 微信刷卡支付= " + reqXml);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String retStr = HttpUtil.sendPost(url, reqXml);
                Log.i(TAG, "WX RSP 微信刷卡支付= " + retStr);
                int result = 0;
                if (retStr.indexOf("<result_code><![CDATA[SUCCESS]]></result_code>") != -1) {
                    result = 1;
                }
                Message msg = new Message();
                msg.what = 2;
                Bundle bundle = new Bundle();
                bundle.putInt("result", result);
                msg.setData(bundle);
                payResultHandler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 微信扫码支付
     *
     * @param mHandler
     */
    public void payByWxScan(final Handler mHandler, String moneyNum) {
        final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        final String reqXml = getRequestXmlByOrder(moneyNum);
        Log.i(TAG, "WX Req 微信扫码支付= " + reqXml);
        System.out.println("**************" + reqXml);
        new Thread(new Runnable() {

            @Override
            public void run() {
                String retStr = HttpUtil.sendPost(url, reqXml);
                Log.i(TAG, "WX RSP 微信扫码支付= " + retStr);
                // 交给主线程处理
                Message msg = new Message();
                msg.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("qrcode", retStr);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 微信条码支付订单参数
     *
     * @param auth_code,moneyNum
     */
    public String getRequestXmlByOrder(String auth_code, String moneyNum) {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", "wx78b06f437f1560e3"); // 公众账号ID
        parameters.put("mch_id", "1360675402"); // 商户号
        parameters.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32)); // 随机字符串
        parameters.put("body", "微信刷卡支付"); // 商品描述
        parameters.put("out_trade_no", String.valueOf(System.currentTimeMillis())); // 商品订单号
        parameters.put("total_fee", moneyNum); // 总金额
        parameters.put("spbill_create_ip", "8.8.8.8"); // 终端IP
        parameters.put("auth_code", auth_code); // 授权码
        String sign = createSign("UTF-8", parameters); // 签名
        parameters.put("sign", sign);
        String requestXML = getRequestXml(parameters);
        return requestXML;
    }

    /**
     * 微信支付签名
     *
     * @param characterEncoding,parameters
     */
    public String createSign(String characterEncoding, Map<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + "4D0B0DE23E371D481BAD405635EAB15C");
        String sign = MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 微信支付xml组装
     *
     * @param parameters
     */
    public String getRequestXml(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");

            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 微信扫码支付订单参数
     *
     * @param moneyNum
     */
    public String getRequestXmlByOrder(String moneyNum) {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", "wx78b06f437f1560e3"); // 公众账号ID
        parameters.put("mch_id", "1360675402"); // 商户号
        parameters.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32)); // 随机字符串
        parameters.put("body", "微信扫码支付"); // 商品描述
        parameters.put("out_trade_no", String.valueOf(System.currentTimeMillis())); // 商品订单号
        parameters.put("total_fee", moneyNum); // 总金额
        parameters.put("spbill_create_ip", "8.8.8.8"); // 终端IP
        parameters.put("notify_url", "http://www.liuhe.com.cn");// 通知地址 接收微信支付异步通知回调地址
        parameters.put("trade_type", "NATIVE");// 交易类型
        String sign = createSign("UTF-8", parameters); // 签名
        parameters.put("sign", sign);
        String requestXML = getRequestXml(parameters);
        return requestXML;
    }
}
