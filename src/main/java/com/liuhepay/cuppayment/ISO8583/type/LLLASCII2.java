package com.liuhepay.cuppayment.ISO8583.type;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class LLLASCII2 extends ISOFiled
{

	public LLLASCII2(String filedValue)
	{
		super(filedValue, filedValue.length());
	}

	public LLLASCII2()
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
