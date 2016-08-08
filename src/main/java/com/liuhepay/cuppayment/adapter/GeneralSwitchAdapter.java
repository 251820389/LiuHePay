package com.liuhepay.cuppayment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.util.MyLog;
import com.liuhepay.cuppayment.util.SharedPreferencesInfo;

public class GeneralSwitchAdapter extends ArrayAdapter<Object>
{
	private Context				mContext;
	private int					mResource;
	private SharedPreferencesInfo	mParameters;
	private boolean				default_value;

	public GeneralSwitchAdapter(Context context, int resource, SharedPreferencesInfo mParameters, boolean default_value)
	{
		super(context, resource);
		mContext = context;
		mResource = resource;
		this.mParameters = mParameters;
		this.default_value = default_value;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		convertView = View.inflate(mContext, mResource, null);
		viewHolder = new ViewHolder();
		viewHolder.trans_switch = (Switch) convertView.findViewById(R.id.lv_switch);
		viewHolder.mTextView = (TextView) convertView.findViewById(R.id.lv_switch_tv);
		convertView.setTag(viewHolder);
		viewHolder.mTextView.setText((String) getItem(position));
		MyLog.i(MyLog.TAG, "mParameter = " + mParameters);
		MyLog.i(MyLog.TAG, "position = " + position);
		MyLog.i(MyLog.TAG, "default_value = " + default_value);
		MyLog.i(MyLog.TAG, "viewHolder.trans_switch = " + viewHolder.trans_switch);
		MyLog.i(MyLog.TAG, "mParameters.getSwitchInfo(position, default_value) = " + mParameters.getSwitchInfo(position, default_value));
		viewHolder.trans_switch.setChecked(mParameters.getSwitchInfo(position, default_value));
		viewHolder.trans_switch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton view, boolean check)
			{
				mParameters.setSwitchInfo(position, check);
			}
		});
		return convertView;
	}

	@Override
	public long getItemId(int position)
	{
		return super.getItemId(position);
	}

	private class ViewHolder
	{
		public TextView	mTextView;
		public Switch	trans_switch;
	}
}
