package com.excel.vo;

public class CompanyVO {
	private int industry;
	private String logo;
	private String name;
	private String vcompany_size;
	private String business_scope;
	private String website;
	private String address;
	private int city;
	private String tag;
	private String detail;
	private String short_name;
	private int nature = 0;
	private transient int row;

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	public int getIndustry() {
		return industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVcompany_size() {
		return vcompany_size;
	}

	public void setVcompany_size(String vcompany_size) {
		this.vcompany_size = vcompany_size;
	}

	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
