package com.type;


import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class LLVar extends ISOFiled{
	
	public LLVar(String filedValue,int len,boolean isPadding,char paddString,String paddingType){
		super(filedValue, len);
		setPaddingStr(paddString);
		setPadding(isPadding);
		setPaddingType(paddingType);
	}
	public LLVar(String filedValue,int len) {
		super(filedValue, len);
	}
	@Override
	public String pack() throws ISOException{
		StringBuffer buf = new StringBuffer();
		buf.append(ISOUtil.padleft(String.valueOf(getLen()), 2, '0'));
		if(PADDING_TYPE_LEFT.equalsIgnoreCase(getPaddingType())){
			buf.append(ISOUtil.padleft(getFiledValue(), getLen(), getPaddingStr()));
		}
		else if(PADDING_TYPE_RINGHT.equalsIgnoreCase(getPaddingType())){
			buf.append(ISOUtil.padright(getFiledValue(), getLen(), getPaddingStr()));
		}else{
			buf.append(getFiledValue());
		}
		return buf.toString();
	}

	@Override
	public String unPack(String buf) throws ISOException {
		return super.unPack(buf);
	}
	
}
