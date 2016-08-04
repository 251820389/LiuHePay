package com.type;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class ISOFiled implements Filed
{

	private String	filedValue;

	private int		len;

	private boolean	isPadding;

	private String	paddingType;

	private char	paddingStr;

	private int		maxLen;

	private int		minLen;

	/**
	 * Ĭ�Ϲ��캯��
	 */
	public ISOFiled()
	{

	}

	public ISOFiled(String filedValue)
	{
		this.filedValue = filedValue;
	}

	/**
	 * ����һ���ƶ����Ⱥ�value���ֶ�
	 * 
	 * @param filedValue
	 * @param len
	 */
	public ISOFiled(String filedValue, int len)
	{
		this.filedValue = filedValue;
		this.len = len;
	}

	public String getPaddingType()
	{
		return paddingType;
	}

	public void setPaddingType(String paddingType)
	{
		this.paddingType = paddingType;
	}

	public int getLen()
	{
		return len;
	}

	public void setLen(int len)
	{
		this.len = len;
	}

	public boolean isPadding()
	{
		return isPadding;
	}

	public void setPadding(boolean isPadding)
	{
		this.isPadding = isPadding;
	}

	public char getPaddingStr()
	{
		return paddingStr;
	}

	public void setPaddingStr(char paddingStr)
	{
		this.paddingStr = paddingStr;
	}

	public int getMaxLen()
	{
		return maxLen;
	}

	public void setMaxLen(int maxLen)
	{
		this.maxLen = maxLen;
	}

	public int getMinLen()
	{
		return minLen;
	}

	public void setMinLen(int minLen)
	{
		this.minLen = minLen;
	}

	public String getFiledValue()
	{
		return filedValue;
	}

	public void setFiledValue(String filedValue)
	{
		this.filedValue = filedValue;
	}

	@Override
	public String pack() throws ISOException
	{
		return this.filedValue;
	}

	@Override
	public String unPack(String buf) throws ISOException
	{
		this.filedValue = ISOUtil.takeFirstN(buf, len);
		return ISOUtil.takeLastN(buf, buf.length() - len);
	}
}
