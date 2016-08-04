package com.liuhepay.cuppayment.ISO8583;

import com.liuhepay.cuppayment.ISO8583.type.BCD;
import com.liuhepay.cuppayment.ISO8583.type.ISOFiled;
import com.liuhepay.cuppayment.util.MyLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;


public class ISOMsg
{
	private ISOMsg	msg;

	public void setISOMsg(ISOMsg msg)
	{
		this.msg = msg;

	}

	public ISOMsg getISOMsg()
	{
		return msg;
	}

	@Override
	protected Object clone()
	{
		ISOMsg o = null;
		try
		{
			o = (ISOMsg) super.clone();
		}
		catch (CloneNotSupportedException ex)
		{
			ex.printStackTrace();
		}

		return o;
	}

	private String					header;
	private Map<Integer,ISOFiled>	bitMap	= new HashMap<Integer,ISOFiled>();
	public String					mak;

	public Map<Integer,ISOFiled> getBitMap()
	{
		return bitMap;
	}

	public void setBitMap(Map<Integer,ISOFiled> bitMap)
	{
		this.bitMap = bitMap;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	private Map<Integer,ISOFiled>	fileds	= new HashMap<Integer,ISOFiled>();
	private StringBuffer			bitMapByte;

	public StringBuffer getBitMapByte()
	{
		return bitMapByte;
	}

	public void setBitMapByte(StringBuffer bitMapByte)
	{
		this.bitMapByte = bitMapByte;
	}

	public void set(Integer filedindex, ISOFiled filed)
	{
		if (filed.getFiledValue() != null && (filedindex == 64 || filed.getFiledValue().length() > 0))
		{
			fileds.put(filedindex, filed);
		}
	}

	public ISOFiled get(Integer filed)
	{
		return fileds.get(filed);
	}

	public Set<Integer> getFileds()
	{
		return fileds.keySet();
	}

	public String getFiledValue(int index)
	{
		String vaule = "";
		if (getISOMsg() != null && getISOMsg().get(index) != null)
		{
			vaule = getISOMsg().get(index).getFiledValue();
		}
		return vaule;
	}

	/**
	 * 打包8583报文
	 *
	 * @param flag 是否打印日志
	 * @return
	 * @throws ISOException
	 */
	public byte[] pack(boolean flag) throws ISOException
	{
		Set<Integer> bitMap = this.getFileds();
		StringBuffer buf = new StringBuffer();
		bitMapByte = new StringBuffer();
		buf.append(header);
		buf.append(this.get(0).pack());
		for (int i = 1; i <= 64; i++)
		{
			if (bitMap.contains(new Integer(i)))
			{
				bitMapByte.append("1");
			}
			else
			{
				bitMapByte.append("0");
			}
		}
		buf.append(ISO8583Utils.packageBitMap(bitMapByte.toString()));
		for (int i = 1; i <= 64; i++)
		{
			if (null != this.get(i))
			{
				buf.append(this.get(i).pack());
				if (flag)
					MyLog.i("组装报文", "第" + i + "域：" + this.get(i).pack());
			}
		}
		String len = Integer.toHexString(ISOUtil.hex2byte(buf.toString()).length);
		len = ISOUtil.padleft(len, 4, '0');
		if (flag)
		{
			MyLog.e("发送报文", len + buf.toString());
		}
		return ISOUtil.concat(ISOUtil.hex2byte(len), ISOUtil.hex2byte(buf.toString()));
	}

	/**
	 * 解包8583报文
	 *
	 * @param buff
	 * @return
	 * @throws ISOException
	 */
	public ISOMsg upPack(String buff) throws ISOException
	{
		MyLog.e("接收报文长度", buff.length() / 2);
		MyLog.e("接收报文", buff);
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setHeader(header);
		isoMsg.setBitMap(new HashMap<Integer,ISOFiled>());
		if (bitMap == null)
		{
			return null;
		}
		if (null == buff)
		{
			return null;
		}
		//读取head信息
		isoMsg.setHeader(ISOUtil.takeFirstN(buff, header.length()));
		buff = ISOUtil.takeLastN(buff, buff.length() - header.length());
		//读取消息类型
		isoMsg.set(0, new BCD(ISOUtil.takeFirstN(buff, 4)));
		buff = ISOUtil.takeLastN(buff, buff.length() - 4);
		//读取位图
		String bitMaps = ISOUtil.takeFirstN(buff, 16);
		buff = ISOUtil.takeLastN(buff, buff.length() - 16);
		//转换位图
		List<Integer> bitMapi = ISO8583Utils.parseBitMap(bitMaps);
		for (int i = 0; i <= 64; i++)
		{

			if (bitMapi.contains(i) && null != bitMap.get(i))
			{

				buff = bitMap.get(i).unPack(buff);
				isoMsg.set(i, bitMap.get(i));
				MyLog.i("接收报文", "第" + i + "域:" + isoMsg.get(i).getFiledValue());
				if (i == 62)
				{
					isoMsg.mak = isoMsg.get(i).getFiledValue();
				}
			}
		}

		return isoMsg;
	}

	/**
	 * 解包8583报文
	 *
	 * @param buff
	 * @return
	 * @throws ISOException
	 */
	public ISOMsg upPack(String buff, int len) throws ISOException
	{
		MyLog.e("接收报文", buff);
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setHeader(header);
		isoMsg.setBitMap(new HashMap<Integer,ISOFiled>());
		if (bitMap == null)
		{
			return null;
		}
		if (null == buff)
		{
			return null;
		}
		//读取head信息
		isoMsg.setHeader(ISOUtil.takeFirstN(buff, len));
		buff = ISOUtil.takeLastN(buff, buff.length() - len);
		//读取消息类型
		isoMsg.set(0, new BCD(ISOUtil.takeFirstN(buff, 4)));
		buff = ISOUtil.takeLastN(buff, buff.length() - 4);
		//读取位图
		String bitMaps = ISOUtil.takeFirstN(buff, 16);
		buff = ISOUtil.takeLastN(buff, buff.length() - 16);
		//转换位图
		List<Integer> bitMapi = ISO8583Utils.parseBitMap(bitMaps);
		for (int i = 0; i <= 64; i++)
		{

			if (bitMapi.contains(i) && null != bitMap.get(i))
			{

				buff = bitMap.get(i).unPack(buff);
				isoMsg.set(i, bitMap.get(i));

				MyLog.i("接收报文", "第" + i + ":" + isoMsg.get(i).getFiledValue());
				if (i == 62)
				{
					isoMsg.mak = isoMsg.get(i).getFiledValue();
				}
			}
		}
		return isoMsg;
	}
}
