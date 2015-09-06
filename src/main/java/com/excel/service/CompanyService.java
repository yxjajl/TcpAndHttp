package com.excel.service;

import java.util.HashMap;

import com.excel.vo.CompanyVO;

public interface CompanyService {
	public int getCount();
	public void initConfig();
	public void insertCompany(CompanyVO companyVO);
	public boolean exist(String name);
	public void updateField(String name,HashMap<String,Object> map);
}
