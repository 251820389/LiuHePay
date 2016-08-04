package com.liuhepay.cuppayment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.base.BaseActivity;
import com.liuhepay.cuppayment.db.dao.UserDao;
import com.liuhepay.cuppayment.sysactivity.SysMainActivity;
import com.liuhepay.cuppayment.util.ScreenSwitch;
import com.liuhepay.cuppayment.util.ToastUtil;

public class MainActivity extends BaseActivity {

	private EditText username, password;
	private Button loginBtn;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	@Override
	protected void initView() {
		super.initView();
		addContentView(R.layout.activity_main);
		setTitleText(R.string.login_btn);
		username = (EditText) findViewById(R.id.usernameText);
		password = (EditText) findViewById(R.id.passwordText);
		loginBtn = (Button) findViewById(R.id.login);
	}

	public void login(View view) {
		String name = username.getText().toString();
		if("".equals(name)){
			ToastUtil.showToast(mContext,getString(R.string.error_login_username));
			return;
		}
		String pwd = password.getText().toString();
		if("".equals(pwd)){
			ToastUtil.showToast(mContext,getString(R.string.error_login_pwd));
			return;
		}
		Log.i(TAG, "name = " + name);
		//TODO 用户名密码校验
		UserDao mDao = new UserDao();
		if(mDao.userLogin(name,pwd)){
			if ("99".equals(name)) {
				ScreenSwitch.switchActivity(mContext, SysMainActivity.class);
			} else if("00".equals(name)){
				//TODO 主管操作员界面
			}else{
				ScreenSwitch.switchActivity(mContext, OperatorMainActivity.class);
			}

		}else{
			ToastUtil.showToast(mContext,getString(R.string.error_login));
			return;
		}
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		type = EXIT;
	}

}
