package com.liuhepay.cuppayment.util;


public class MyLog
{
	public static boolean	flag	= true;
	public static String	TAG		= "LiuhePay";

	public static void e(String value)
	{
		if (flag)
		{
			android.util.Log.e(TAG, value);
		}
	}

	public static void e(String tag, String value)
	{
		if (flag)
		{
			android.util.Log.e(tag, value + "");
		}
	}

	public static void e(String tag, long recordString)
	{
		if (flag)
		{
			android.util.Log.e(tag, recordString + "");
		}
	}

	public static void e(String tag, int value)
	{
		if (flag)
		{
			android.util.Log.e(tag, value + "");
		}
	}

	public static void i(String value)
	{
		if (flag)
		{
			android.util.Log.i(TAG, value);
		}
	}

	public static void i(String tag, String value)
	{
		if (flag)
		{
			android.util.Log.i(tag, value + "");
		}
	}

	public static void i(String tag, int value)
	{
		if (flag)
		{
			android.util.Log.i(tag, value + "");
		}
	}
}
