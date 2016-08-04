package com.liuhepay.cuppayment.util;

public class ConfigUtil {
	public static final int WXSCANPAY = 0;
	public static final int WXPAY = 1;
	public static final int ALIPAYSCANPAY = 2;
	public static final int ALIPAYPAY = 3;
	public static final int SALE = 10001;
	public static final int PREAUTH = 10101;
	public static final int PREAUTHCOMPLETE = 10102;

	// 收款金额转换
	public static String covertPoint(String price) {
		return String.valueOf((double) (Double.parseDouble(price)));
	}
}
