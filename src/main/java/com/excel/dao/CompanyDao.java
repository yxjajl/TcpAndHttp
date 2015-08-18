package com.excel.dao;

import com.excel.vo.CompanyVO;

public interface CompanyDao {
	public int getCount();
	public void insertCompany(CompanyVO companyVO);
	public int getCountByName(String name);
}
