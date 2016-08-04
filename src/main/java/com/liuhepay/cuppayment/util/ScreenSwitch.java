package com.liuhepay.cuppayment.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ScreenSwitch
{

	public static void switchActivity(Context context, Class<?> descClass, Bundle bundle)
	{
		Class<?> mClass = context.getClass();
		if (mClass == descClass)
		{
			return;
		}
		try
		{
			Intent intent = new Intent();
			intent.setClass(context, descClass);
			if (bundle != null)
			{
				intent.putExtras(bundle);
			}
			((Activity) context).startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void switchActivity(Context context, Class<?> descClass)
	{
		Class<?> mClass = context.getClass();
		if (mClass == descClass)
		{
			return;
		}
		try
		{
			Intent intent = new Intent();
			intent.setClass(context, descClass);
			((Activity) context).startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void switchActivity(Context context, Class<?> descClass, int flag)
	{
		Class<?> mClass = context.getClass();
		if (mClass == descClass)
		{
			return;
		}
		try
		{
			Intent intent = new Intent();
			intent.setClass(context, descClass);
			intent.setFlags(flag);
			((Activity) context).startActivity(intent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
