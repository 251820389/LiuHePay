package com.type;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class LLLASCII extends ISOFiled
{

	public LLLASCII(String filedValue)
	{
		super(filedValue, filedValue.length());
	}

	public LLLASCII()
	{}

	@Override
	public String pack() throws ISOException
	{
		StringBuffer buf = new StringBuffer();
		buf.append(ISOUtil.padleft(String.valueOf(getFiledValue().length()), 4, '0')).append(ISOUtil.hexString(getFiledValue().getBytes()));
		return buf.toString();
	}

	@Override
	public String unPack(String buf) throws ISOException
	{
		// ��ȡ�����ַ�����
		String len = ISOUtil.takeFirstN(buf, 4);
		buf = ISOUtil.takeLastN(buf, buf.length() - 4);
		int leni = ISOUtil.parseInt(len);
		String pacString = ISOUtil.takeFirstN(buf, leni * 2);
		buf = ISOUtil.takeLastN(buf, buf.length() - leni * 2);
		setFiledValue(new String(ISOUtil.hex2byte(pacString)));
		setLen(leni);
		return buf;
	}

}
