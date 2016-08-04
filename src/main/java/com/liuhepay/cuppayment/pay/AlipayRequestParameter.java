package com.liuhepay.cuppayment.pay;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.liuhepay.cuppayment.alipay.Config;

public class AlipayRequestParameter {
	private static final String TAG = "LiuhePay";
	String app_id = Config.APPID;
	String subject = "六合通讯测试";
	/**
	 * 支付宝条码支付
	 * 
	 * @param data
	 * @param payResultHandler
	 * @param moneyNum
	 */
	public void payByAlipay(final String data, final Handler payResultHandler,
			final String moneyNum) {
		final String url = "https://openapi.alipay.com/gateway.do";
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String method = "alipay.trade.pay";
				String sign = "";
				String biz_content = "";

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String time_expire = sdf.format(System.currentTimeMillis() + 24
						* 60 * 60 * 1000);
				String timestamp = sdf.format(System.currentTimeMillis());

				// 扫码支付请求参数
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("out_trade_no",
						String.valueOf(System.currentTimeMillis()));
				map.put("scene", "bar_code");
				map.put("auth_code", data);
				map.put("total_amount", moneyNum);
				map.put("subject", subject);
				map.put("time_expire", time_expire);

				biz_content = JSON.toJSONString(map);

				// 公共请求参数
				Map<String, String> commonMap = new HashMap<String, String>();
				commonMap.put("app_id", app_id);
				commonMap.put("method", method);
				commonMap.put("charset", "UTF-8");
				commonMap.put("sign_type", "RSA");
				commonMap.put("timestamp", timestamp);
				commonMap.put("version", "1.0");
				commonMap.put("biz_content", biz_content);
				sign = createSign(commonMap);
				commonMap.put("sign", sign);
				String params = null;
				try {
					params = buildQuery(commonMap, "UTF-8");
				} catch (IOException e) {
					e.printStackTrace();
				}

				Log.e(TAG, "支付宝条码支付：params = "+params);
				String retStr = HttpUtil.sendPost(url, params);
				Log.e(TAG, "支付宝条码支付：retStr = "+retStr);

				// 交给主线程处�?
				Message msg = new Message();
				msg.what = 0;
				Bundle bundle = new Bundle();
				bundle.putString("result", retStr);
				msg.setData(bundle);
				payResultHandler.sendMessage(msg);
			}
		}).start();
	}

	public static String buildQuery(Map<String, String> params, String charset)
			throws IOException {
		if ((params == null) || (params.isEmpty())) {
			return null;
		}
		StringBuilder query = new StringBuilder();
		Set<Map.Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;
		for (Map.Entry<String, String> entry : entries) {
			String name = (String) entry.getKey();
			String value = (String) entry.getValue();
			if (name != null && value != null && !name.equals("")
					&& !value.equals("")) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}
				query.append(name).append("=")
						.append(URLEncoder.encode(value, charset));
			}
		}
		return query.toString();
	}

	private static String PRIVATE_KEY = Config.RSA_RRIVATE_KEY;

	private static String PRIVATE_KEY2 = "N+";

	private static String PUBLIC_KEY = "N+";

	public String createSign(Map<String, String> parameters) {
		// OPEN SSL 加密
		String encryptByte = null;
		try {
			encryptByte = AlipaySignature.rsaSign(parameters, PRIVATE_KEY, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptByte;
	}

	/**
	 * 支付宝扫码支付
	 * 
	 * @param moneyNum
	 * @param mHandler
	 */
	public void payByAlipayScan(final String moneyNum, final Handler mHandler) {
		final String url = "https://openapi.alipay.com/gateway.do";
		new Thread(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				// TODO Auto-generated method stub

				String method = "alipay.trade.precreate";
				String sign = "";
				String biz_content = "";

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String time_expire = sdf.format(System.currentTimeMillis() + 24
						* 60 * 60 * 1000);
				String timestamp = sdf.format(System.currentTimeMillis());

				// 扫码支付请求参数
				HashMap map = new HashMap();
				map.put("out_trade_no",
						String.valueOf(System.currentTimeMillis()));
				map.put("total_amount", moneyNum);
				map.put("subject", subject);
				map.put("time_expire", time_expire);

				biz_content = JSON.toJSONString(map);

				// 公共请求参数
				Map commonMap = new HashMap();
				commonMap.put("app_id", app_id);
				commonMap.put("method", method);
				commonMap.put("charset", "UTF-8");
				commonMap.put("sign_type", "RSA");
				commonMap.put("timestamp", timestamp);
				commonMap.put("version", "1.0");
				commonMap.put("biz_content", biz_content);
				sign = createSign(commonMap);
				commonMap.put("sign", sign);
				String params = null;
				try {
					params = buildQuery(commonMap, "UTF-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				Log.e(TAG, "params = "+params);
				String retStr = HttpUtil.sendPost(url, params);
				Log.e(TAG, "retStr = "+retStr);

				// 交给主线程处理
				Message msg = new Message();
				msg.what = 1;
				Bundle bundle = new Bundle();
				bundle.putString("qrcode", retStr);
				msg.setData(bundle);
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	public String getRequestXml(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
					|| "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
