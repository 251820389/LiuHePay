package com.liuhepay.cuppayment.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LEO on 2016/7/25.
 */
@DatabaseTable(tableName="sale")
public class SaleTable {
    @DatabaseField(id=true)
    private String MID;
    @DatabaseField(canBeNull=false)
    private String TID;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull=false)
    private String acq;//收单行
    @DatabaseField(canBeNull = false)
    private String iss;//发卡行
    @DatabaseField(canBeNull = false)
    private String cardNo;
    @DatabaseField(canBeNull = false)
    private String operatorId;
    @DatabaseField(canBeNull = false)
    private String transType;
    @DatabaseField(canBeNull = false)
    private String batchNo;
    @DatabaseField(canBeNull = false)
    private String refNo;
    @DatabaseField(canBeNull = true)
    private String authNo;
    @DatabaseField
    private String amount;
    @DatabaseField
    private String date;
    @DatabaseField
    private String time;
    //ic card args
    @DatabaseField
    private String tvr;//终端验证结果
    @DatabaseField
    private String tsi;//交易状态信息
    @DatabaseField
    private String aid;
    @DatabaseField
    private String atc;
    @DatabaseField
    private String iad;
    @DatabaseField
    private String applLabel;
    @DatabaseField
    private String applName;
    @DatabaseField
    private String tac;

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcq() {
        return acq;
    }

    public void setAcq(String acq) {
        this.acq = acq;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTvr() {
        return tvr;
    }

    public void setTvr(String tvr) {
        this.tvr = tvr;
    }

    public String getTsi() {
        return tsi;
    }

    public void setTsi(String tsi) {
        this.tsi = tsi;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getIad() {
        return iad;
    }

    public void setIad(String iad) {
        this.iad = iad;
    }

    public String getApplLabel() {
        return applLabel;
    }

    public void setApplLabel(String applLabel) {
        this.applLabel = applLabel;
    }

    public String getApplName() {
        return applName;
    }

    public void setApplName(String applName) {
        this.applName = applName;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    @Override
    public String toString() {
        return "SaleTable{" +
                "\n  MID='" + MID  +
                "\n  TID='" + TID  +
                "\n  name='" + name  +
                "\n  acq='" + acq  +
                "\n  iss='" + iss  +
                "\n  cardNo='" + cardNo  +
                "\n  operatorId='" + operatorId  +
                "\n  transType='" + transType  +
                "\n  batchNo='" + batchNo  +
                "\n  refNo='" + refNo  +
                "\n  authNo='" + authNo  +
                "\n  amount='" + amount  +
                "\n  date='" + date  +
                "\n  time='" + time  +
                "\n  tvr='" + tvr  +
                "\n  tsi='" + tsi  +
                "\n  aid='" + aid  +
                "\n  atc='" + atc  +
                "\n  iad='" + iad  +
                "\n  applLabel='" + applLabel  +
                "\n  applName='" + applName  +
                "\n  tac='" + tac  +
                '}';
    }
}
