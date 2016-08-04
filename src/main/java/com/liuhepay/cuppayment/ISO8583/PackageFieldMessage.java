package com.liuhepay.cuppayment.ISO8583;


import android.annotation.SuppressLint;
import android.content.Context;

import com.liuhepay.cuppayment.ISO8583.type.ASCII;
import com.liuhepay.cuppayment.ISO8583.type.BCD;
import com.liuhepay.cuppayment.ISO8583.type.BINARY;
import com.liuhepay.cuppayment.ISO8583.type.ICDATA;
import com.liuhepay.cuppayment.ISO8583.type.ISOFiled;
import com.liuhepay.cuppayment.ISO8583.type.LLASCII;
import com.liuhepay.cuppayment.ISO8583.type.LLBCD;
import com.liuhepay.cuppayment.ISO8583.type.LLLASCII;
import com.liuhepay.cuppayment.ISO8583.type.LLLBCD;
import com.liuhepay.cuppayment.util.HexStringUtil;

import java.util.HashMap;
import java.util.Map;


@SuppressLint("UseSparseArrays")
public abstract class PackageFieldMessage {
    protected ISOMsg mMessager = null;
    protected Map<Integer, ISOFiled> mBitMap = null;
    protected Context context;
    //	protected ReadParameters		mParameters;
    protected String mHeader = "613200320002";
    //	protected ConfigurationBean		mConfigurationBean;
    protected MessagerType mMessgerType;
    //	protected int					tagList[]	= { Tag.TAG_CRYPTOGRAM, Tag.TAG_CRYPTOGRAM_INFO_DATA, Tag.TAG_ISSUER_APP_DATA, Tag.TAG_UNPREDICTABLE_NUMBER, Tag.TAG_ATC, Tag.TAG_TVR,
    //			Tag.TAG_TRANSACTION_DATE, Tag.TAG_TRANSACTION_TIME, Tag.TAG_TRANSACTION_TYPE, Tag.TAG_AMOUNT, Tag.TAG_TRANS_CURRENCY_CODE,
    //			Tag.TAG_AIP, Tag.TAG_TERM_COUNTRY_CODE, Tag.TAG_AMOUNT_OTHER, Tag.TAG_TERMINAL_CAPABILITIES };

    /**
     * 交易的类型
     *
     * @author jollye
     */
    protected enum MessagerType {
        LOGIN(0), SALE(1), DOWNLOAD(2), MANAGE(3), REVERSAL(4);
        private int type;

        MessagerType(int value) {
            type = value;
        }

        public int getType() {
            return type;
        }
    }

    public PackageFieldMessage(Context context) {
        this.context = context;
        //		mParameters = ReadParameters.getInstance(context);
    }

    /**
     * 打包报文头
     */
    private void packageHeader() {
        mMessager = new ISOMsg();
        //		mConfigurationBean = mParameters.getConfigurationInfo();
        //		mMessager.setHeader(mConfigurationBean.getTPDU() + mHeader);
    }

    /**
     * 组装接收报文所需要的域
     */
    private void packageReceiveField() {
        mBitMap = new HashMap<Integer, ISOFiled>();
        mBitMap.put(2, new LLBCD());
        mBitMap.put(3, new BCD("", 6));
        mBitMap.put(4, new BCD("", 12));
        mBitMap.put(11, new BCD("", 6));
        mBitMap.put(12, new BCD("", 6));
        mBitMap.put(13, new BCD("", 4));
        mBitMap.put(14, new BCD("", 4));
        mBitMap.put(15, new BCD("", 4));
        mBitMap.put(23, new BCD("", 3));
        mBitMap.put(25, new BCD("", 2));
        mBitMap.put(32, new LLBCD());
        mBitMap.put(37, new ASCII("", 12));
        mBitMap.put(38, new ASCII("", 6));
        mBitMap.put(39, new ASCII("", 2));
        mBitMap.put(41, new ASCII("", 8));
        mBitMap.put(42, new ASCII("", 15));
        mBitMap.put(44, new LLASCII());
        mBitMap.put(49, new ASCII("", 3));
        mBitMap.put(53, new BCD("", 16));
        mBitMap.put(54, new LLLASCII());
        mBitMap.put(55, new ICDATA());
        mBitMap.put(59, new LLLASCII(""));
        mBitMap.put(60, new LLLBCD());
        mBitMap.put(62, new ICDATA());
        mBitMap.put(63, new LLLASCII());
        mBitMap.put(64, new BINARY("", 16));
        addFieldOrRevise();
        mMessager.setBitMap(mBitMap);
    }

    /**
     * 一些特殊交易 不适用通用接收域在该方法可以修改接收域的类型
     */
    protected void addFieldOrRevise() {
    }

    ;

    protected abstract ISOMsg addField();

    /**
     * 报文打包
     */
    public void packageInfo() {
        packageHeader();
        packageReceiveField();
        packagePartInfo();
    }

    /**
     * 通用发送的报文
     */
    private void packagePartInfo() {
        //		int type = TransBean.getInstance().getTransType();
        if (mBitMap != null) {
            String feild_22 = "";
            //			mMessager.set(41, new ASCII(mConfigurationBean.getTID()));
            //			mMessager.set(42, new ASCII(mConfigurationBean.getMID()));
            if (mMessgerType == MessagerType.SALE) {
                feild_22 = "05";
                //				if (TransBean.isInputPwd)
                //				{
                mMessager.set(22, new BCD(feild_22 + "1"));
                mMessager.set(26, new BCD("12"));
//                mMessager.set(52, new BINARY(TransBean.getInstance().getPinBlock()));
                //				}
                //				else
                //				{
                //					mMessager.set(22, new BCD(feild_22 + "0"));
                //				}
            }
        }
    }


    /**
     * 获取mac的值
     *
     * @return
     */
    public String getMac(String macMsg) {
        byte macOut[] = new byte[8];
        /*byte[] inData = HexStringUtil.hexStringToBytes(macMsg.substring(26));
		int ret = com.jolly.ped.Pci.getMac(inData, MacAlgIndicator.CUP_MAC, (byte) 1, macOut);
		if (ret != 0)
		{
			MyLog.e("getMac fail,ret = " + ret);
			ToastUtil.showToast(context, "getMac fail,ret = " + ret);
		}*/
		String macData = HexStringUtil.byteArrayToHexstring(macOut, 0, 8);
        return macData;
    }

    /**
     * 获取des的值
     *
     * @return
     */
    public String getDes(String desMsg) {
		/*if (desMsg == null || "".equals(desMsg))
		{
			return "";
		}
		String desData = desMsg.substring(desMsg.length() - 16, desMsg.length() - 1);
		byte desOut[] = new byte[8];
		byte[] inData = HexStringUtil.hexStringToBytes(desData);
		int ret = com.jolly.ped.Pci.desEncrypt(inData, (byte) 1, desOut);
		if (ret != 0)
		{
			MyLog.e("desEncrypt fail,ret = " + ret);
			ToastUtil.showToast(context, "desEncrypt fail,ret = " + ret);
		}
		String macData = HexStringUtil.byteArrayToHexstring(desOut, 0, 8);
		desMsg = desMsg.replace(desData, macData);*/
        return desMsg;
    }
}
