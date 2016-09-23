package com.test;

public class UniversalDTO {
	private String avoirdupois;//投保人体重
	private String stature;//投保人身高
	private String agentCode;//工号
	private String orderCode;//订单号
	//投保人信息
	private String holderName;
	private String holderId;
	private String holderPhone;
	private String holderMail;
	private String areacode;//服务地区
	private String occupation; //职业类型
	private String detailAddress;//联系地址
	private String province;//省份
	private String city;
	private String district;

	//保障内容
	private String mainPremium;
	private String insuranceFlag; //豁免 是/否
	private String accountFlag;//分红及生存金转入万能险账户
	private String payYear;//缴费年限
	private String payType;//缴费方式

	//被保人信息
	private String insurerName;
	private String insurerId;
	private String insurerPhone;

	private String insrelationapp;//与投保人关系 （1本人2子女）

	//保费账户信息
	private String bankType;//借记卡 /信用卡
	private String bankId;//开户行
	private String bankCcard;//银行卡号
	private String bankValidity;//信用卡有效期
	private String bankCardMonth;
	private String bankCardYear;

	private String payStatus;

	private String location;//坐席区域
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAvoirdupois() {
		return avoirdupois;
	}

	public void setAvoirdupois(String avoirdupois) {
		this.avoirdupois = avoirdupois;
	}

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderId() {
		return holderId;
	}

	public void setHolderId(String holderId) {
		this.holderId = holderId;
	}

	public String getHolderPhone() {
		return holderPhone;
	}

	public void setHolderPhone(String holderPhone) {
		this.holderPhone = holderPhone;
	}

	public String getHolderMail() {
		return holderMail;
	}

	public void setHolderMail(String holderMail) {
		this.holderMail = holderMail;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMainPremium() {
		return mainPremium;
	}

	public void setMainPremium(String mainPremium) {
		this.mainPremium = mainPremium;
	}

	public String getInsuranceFlag() {
		return insuranceFlag;
	}

	public void setInsuranceFlag(String insuranceFlag) {
		this.insuranceFlag = insuranceFlag;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	public String getPayYear() {
		return payYear;
	}

	public void setPayYear(String payYear) {
		this.payYear = payYear;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerPhone() {
		return insurerPhone;
	}

	public void setInsurerPhone(String insurerPhone) {
		this.insurerPhone = insurerPhone;
	}

	public String getInsrelationapp() {
		return insrelationapp;
	}

	public void setInsrelationapp(String insrelationapp) {
		this.insrelationapp = insrelationapp;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankCcard() {
		return bankCcard;
	}

	public void setBankCcard(String bankCcard) {
		this.bankCcard = bankCcard;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getBankValidity() {
		return bankValidity;
	}

	public void setBankValidity(String bankValidity) {
		this.bankValidity = bankValidity;
	}

	public String getBankCardMonth() {
		return bankCardMonth;
	}

	public void setBankCardMonth(String bankCardMonth) {
		this.bankCardMonth = bankCardMonth;
	}

	public String getBankCardYear() {
		return bankCardYear;
	}

	public void setBankCardYear(String bankCardYear) {
		this.bankCardYear = bankCardYear;
	}

}