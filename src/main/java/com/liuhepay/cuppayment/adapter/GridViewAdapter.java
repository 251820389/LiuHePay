package com.liuhepay.cuppayment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private String text[];
	private String imageName[];
	public GridViewAdapter(Context context,String[] text,String[] imageName) {
		this.context = context;
		this.text = text;
		this.imageName = imageName;
	}

	@Override
	public int getCount() {
		return text.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}

}
