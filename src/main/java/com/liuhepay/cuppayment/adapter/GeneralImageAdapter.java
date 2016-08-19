package com.liuhepay.cuppayment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.util.GetResourceId;

/**
 * 作者 jollye 日期 2016/1/14
 */
public class GeneralImageAdapter extends ArrayAdapter<Object>
{
	private Context	mContext;
	private int		mResource;
	private String	mDrawablename;
//	private boolean	flag;			//true为gridview false为listview

	public GeneralImageAdapter(Context context, int resource, String drawablename, boolean flag)
	{

		super(context, resource);
		mContext = context;
		mResource = resource;
		mDrawablename = drawablename;
//		this.flag = flag;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null)
		{
			convertView = View.inflate(mContext, mResource, null);
			viewHolder = new ViewHolder();
			viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_item);
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_item);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		int id = GetResourceId.getXmlId(mContext, mDrawablename + position);
		if (id == 0)
		{
			id = R.xml.icon_sel0;
		}
		viewHolder.mImageView.setBackgroundResource(id);
		viewHolder.mTextView.setText((String) getItem(position));
		return convertView;
	}

	@Override
	public long getItemId(int position)
	{
		return super.getItemId(position);
	}

	private class ViewHolder
	{
		public ImageView	mImageView;
		public TextView		mTextView;
	}
}
