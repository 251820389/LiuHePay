package com.liuhepay.cuppayment.crash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.util.ToastUtil;

public class Crash implements UncaughtExceptionHandler {

	public static final String TAG = "CrashHandler";

	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static Crash INSTANCE = new Crash();
	private Context mContext;
	private Map<String, String> infos = new HashMap<String, String>();

	private Crash() {
	}

	public static Crash getInstance() {
		return INSTANCE;
	}

	public void init(Context context) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			System.exit(1);
		}
	}

	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
//		new Thread() {
//			@Override
//			public void run() {
//				Looper.prepare();
//				ToastUtil.showToast(mContext, R.string.elec_cash_main_activity);
//				Looper.loop();
//			}
//		}.start();
		collectDeviceInfo(mContext);
		saveCrashInfo2File(ex);
		return true;
	}

	public void collectDeviceInfo(Context ctx) {
		try {

			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, "an error occured when collect package info", e);
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Log.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
	}

	private String saveCrashInfo2File(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		Log.e("LiuhePay", "error:" + result);
		sb.append(result);
		try {
			// String time = formatter.format(new Date());
			String fileName = "crash_.log";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator + "LiuhePay/";
				File dir = new File(path);
				if (!dir.isDirectory()) {
					dir.mkdirs();
				}
				File file = new File(path, fileName);
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}
}
