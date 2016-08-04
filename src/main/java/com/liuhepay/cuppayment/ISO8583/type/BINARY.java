package com.liuhepay.cuppayment.ISO8583.type;

public class BINARY extends ISOFiled
{
	/**
	 * ����һ���ƶ����Ⱥ�value���ֶ�
	 * 
	 * @param filedValue
	 * @param len
	 */
	public BINARY(String filedValue)
	{
		super(filedValue, filedValue.length());
	}

	/**
	 * ����һ���ƶ����Ⱥ�value���ֶ�
	 * 
	 * @param filedValue
	 * @param len
	 */
	public BINARY(String filedValue, int len)
	{
		super(filedValue, len);
	}
}
