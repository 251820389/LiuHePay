package com.liuhepay.cuppayment.ISO8583;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.jpos.iso.ISOException;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;

public class ISO8583Utils
{
	private static final String	TAG						= "ISO8583Utils";
	public static final int		ERR_PACKAGER_SEND		= -5000;
	private static final int	ERR_RECEVICE_LENGTH		= -5001;
	private static final int	ERR_PACKAGER_RECEVICE	= -5002;
	private static final int	SUCCEED					= 0;
	static Socket				soc;
	static Timer				timer;

	//	private static iProgressDlg	dig						= null;

	/**
	 * ��01��ɵ�λͼ��ת����16���Ƶ�λͼ��
	 * 
	 * @param bitMap 01��ɵ�λͼ��
	 * @return 16���Ƶ�λͼ��
	 */
	public static StringBuffer packageBitMap(String bitMap)
	{
		if (bitMap == null || bitMap.length() != 64)
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, k = 0; i < bitMap.length() / 4; i++, k += 4)
		{
			buffer.append(binaryToHex(bitMap.substring(k, k + 4)));
		}
		return buffer;
	}

	/**
	 * ��4λ��01��ת����16����
	 * 
	 * @param str 4λ01��
	 * @return 01����16���Ʊ�ʾ(0-f)
	 */
	public static String binaryToHex(String str)
	{
		if (str == null || str.length() != 4)
		{
			return null;
		}
		if (ISO8583Const.HEX_0.equals(str))
		{
			return ISO8583Const.BINARY_0;
		}
		else if (ISO8583Const.HEX_1.equals(str))
		{
			return ISO8583Const.BINARY_1;
		}
		else if (ISO8583Const.HEX_2.equals(str))
		{
			return ISO8583Const.BINARY_2;
		}
		else if (ISO8583Const.HEX_3.equals(str))
		{
			return ISO8583Const.BINARY_3;
		}
		else if (ISO8583Const.HEX_4.equals(str))
		{
			return ISO8583Const.BINARY_4;
		}
		else if (ISO8583Const.HEX_5.equals(str))
		{
			return ISO8583Const.BINARY_5;
		}
		else if (ISO8583Const.HEX_6.equals(str))
		{
			return ISO8583Const.BINARY_6;
		}
		else if (ISO8583Const.HEX_7.equals(str))
		{
			return ISO8583Const.BINARY_7;
		}
		else if (ISO8583Const.HEX_8.equals(str))
		{
			return ISO8583Const.BINARY_8;
		}
		else if (ISO8583Const.HEX_9.equals(str))
		{
			return ISO8583Const.BINARY_9;
		}
		else if (ISO8583Const.HEX_A.equals(str))
		{
			return ISO8583Const.BINARY_A;
		}
		else if (ISO8583Const.HEX_B.equals(str))
		{
			return ISO8583Const.BINARY_B;
		}
		else if (ISO8583Const.HEX_C.equals(str))
		{
			return ISO8583Const.BINARY_C;
		}
		else if (ISO8583Const.HEX_D.equals(str))
		{
			return ISO8583Const.BINARY_D;
		}
		else if (ISO8583Const.HEX_E.equals(str))
		{
			return ISO8583Const.BINARY_E;
		}
		else if (ISO8583Const.HEX_F.equals(str))
		{
			return ISO8583Const.BINARY_F;
		}
		return null;
	}

	/**
	 * ����16���Ƶ�bitMap�����ж��ĸ�������ֵ���������λ�÷���һ��int����
	 * 
	 * @param bitMap
	 * @return int���飬���е�ֵ��ʾ�Ǹ�����ֵ
	 */
	public static List<Integer> parseBitMap(String bitMap)
	{
		List<Integer> list = new ArrayList<Integer>();
		StringBuffer buffer = new StringBuffer();
		char[] ch = bitMap.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			buffer.append(hexToBinary(ch[i]));
		}

		for (int k = 0; k < buffer.length(); k++)
		{
			if (buffer.charAt(k) == '1')
			{
				list.add(k + 1);
			}
		}
		return list;
	}

	/**
	 * ��һ��16�����ַ�ת����2����
	 * 
	 * @return 2���Ʊ�ʾ
	 */
	public static String hexToBinary(char ch)
	{
		String c = String.valueOf(ch);
		if (ISO8583Const.BINARY_0.equals(c))
		{
			return ISO8583Const.HEX_0;
		}
		else if (ISO8583Const.BINARY_1.equals(c))
		{
			return ISO8583Const.HEX_1;
		}
		else if (ISO8583Const.BINARY_2.equals(c))
		{
			return ISO8583Const.HEX_2;
		}
		else if (ISO8583Const.BINARY_3.equals(c))
		{
			return ISO8583Const.HEX_3;
		}
		else if (ISO8583Const.BINARY_4.equals(c))
		{
			return ISO8583Const.HEX_4;
		}
		else if (ISO8583Const.BINARY_5.equals(c))
		{
			return ISO8583Const.HEX_5;
		}
		else if (ISO8583Const.BINARY_6.equals(c))
		{
			return ISO8583Const.HEX_6;
		}
		else if (ISO8583Const.BINARY_7.equals(c))
		{
			return ISO8583Const.HEX_7;
		}
		else if (ISO8583Const.BINARY_8.equals(c))
		{
			return ISO8583Const.HEX_8;
		}
		else if (ISO8583Const.BINARY_9.equals(c))
		{
			return ISO8583Const.HEX_9;
		}
		else if (ISO8583Const.BINARY_A.equals(c))
		{
			return ISO8583Const.HEX_A;
		}
		else if (ISO8583Const.BINARY_B.equals(c))
		{
			return ISO8583Const.HEX_B;
		}
		else if (ISO8583Const.BINARY_C.equals(c))
		{
			return ISO8583Const.HEX_C;
		}
		else if (ISO8583Const.BINARY_D.equals(c))
		{
			return ISO8583Const.HEX_D;
		}
		else if (ISO8583Const.BINARY_E.equals(c))
		{
			return ISO8583Const.HEX_E;
		}
		else if (ISO8583Const.BINARY_F.equals(c))
		{
			return ISO8583Const.HEX_F;
		}
		return null;
	}

	public static ISOMsg sendIcParameterAndKey(ISOMsg msg, final Activity act) throws IOException
	{

		int ret = -1;
		int wantedLen = -1;
		byte[] rsp = new byte[1024];
		/*ReadParameters mParameters = ReadParameters.getInstance(act);
		ConfigurationBean mConfigurationBean = mParameters.getConfigurationInfo();
		ret = CommonSocket.connectToHost(mConfigurationBean.getIp(), Integer.parseInt(mConfigurationBean.getPort()), mConfigurationBean.getTimeOutl());
		MyLog.e(TAG, ret);
		ISOMsg receiverMsg = null;
		if (ret == 0)
		{
			try
			{
				ret = CommonSocket.sendBytes(msg.pack(true), msg.pack(false).length, mConfigurationBean.getTimeOutl() * 1000);
			}
			catch (ISOException e)
			{
				MyLog.e(TAG, "���Ĵ���쳣");
				e.printStackTrace();
				CommonSocket.close();
				return null;
			}
			ret = CommonSocket.receiveBytes(rsp, 2, mConfigurationBean.getTimeOutl() * 1000);
			MyLog.e("���ձ��ĳ���", HexStringUtil.byteArrayToHexstring(rsp, 0, 2) + "\r\n����:" + ret);
			if (ret != 2)
			{
				return null;
			}
			else
			{
				wantedLen = Integer.parseInt(HexStringUtil.byteArrayToHexstring(rsp, 0, 2), 16);
				ret = CommonSocket.receiveBytes(rsp, wantedLen, mConfigurationBean.getTimeOutl() * 1000);
				MyLog.e("���ձ���", HexStringUtil.byteArrayToHexstring(rsp, 0, wantedLen) + "\r\nret " + ret);
				if (ret != wantedLen)
				{
					MyLog.e(TAG, "receiveBytes ret=" + ret);
				}
			}

			try
			{
				receiverMsg = msg.upPack(HexStringUtil.byteArrayToHexstring(rsp, 0, wantedLen));
			}
			catch (ISOException e)
			{
				e.printStackTrace();
				MyLog.e(TAG, "���ձ��Ľ���쳣");
				CommonSocket.close();
				return null;
			}
		}
		else
		{
			throw new IOException();
		}
		return receiverMsg;*/
		return null;

	}

	/**
	 * ���ͱ���
	 * 
	 * @param msgIn ���͵ı���
	 * @param msgOut ���յı���
	 * @return ״̬��
	 */
	public static int sendAndReceive(ISOMsg msgIn, ISOMsg msgOut, final Context context)
	{
		/*SystemClock.sleep(10);
		int ret = -1;
		int wantedLen = -1;
		byte[] rsp = new byte[1024];
		ReadParameters mParameters = ReadParameters.getInstance(context);
		ConfigurationBean mConfigurationBean = mParameters.getConfigurationInfo();
		ret = CommonSocket.connectToHost(mConfigurationBean.getIp(), Integer.parseInt(mConfigurationBean.getPort()), mConfigurationBean.getTimeOutl());
		if (ret != 0)
		{
			return ret;
		}
		if (ret == 0)
		{
			try
			{
				SystemClock.sleep(10);
				byte[] pack = msgIn.pack(true);
				ret = CommonSocket.sendBytes(pack, pack.length, mConfigurationBean.getTimeOutl() * 1000);
				if (ret != 0)
				{
					return ret;
				}
			}
			catch (ISOException e)
			{
				MyLog.e(TAG, "�������ݱ��Ĵ���쳣");
				e.printStackTrace();
				CommonSocket.close();
				return ERR_PACKAGER_SEND;
			}
			ret = CommonSocket.receiveBytes(rsp, 2, mConfigurationBean.getTimeOutl() * 1000);
			if (ret != 2)
			{
				MyLog.e("���ձ��ĳ���", "���󳤶�:" + ret);
				return ERR_RECEVICE_LENGTH;
			}
			else
			{
				wantedLen = Integer.parseInt(HexStringUtil.byteArrayToHexstring(rsp, 0, 2), 16);
				ret = CommonSocket.receiveBytes(rsp, wantedLen, mConfigurationBean.getTimeOutl() * 1000);
				if (ret != wantedLen)
				{
					MyLog.e(TAG, "receiveBytes ret=" + ret);
				}
			}
		}
		try
		{
			msgOut.setISOMsg(msgIn.upPack(HexStringUtil.byteArrayToHexstring(rsp, 0, wantedLen)));
		}
		catch (ISOException e)
		{
			e.printStackTrace();
			MyLog.e(TAG, "���ձ��Ľ���쳣");
			CommonSocket.close();
			return ERR_PACKAGER_RECEVICE;
		}
		return SUCCEED;*/
		return 0;
	}
}
