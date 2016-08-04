package com.type;

import org.jpos.iso.ISOException;

public interface Filed
{
	//�Ҳ�λ
	public static String	PADDING_TYPE_RINGHT	= "RINGHT";

	//��λ
	public static String	PADDING_TYPE_LEFT	= "LEFT";

	/**
	 * ����ֶ�
	 * 
	 * @return
	 */
	public String pack() throws ISOException;

	/**
	 * �������
	 * 
	 * @param buf
	 * @return
	 */
	public String unPack(String buf) throws ISOException;
}
