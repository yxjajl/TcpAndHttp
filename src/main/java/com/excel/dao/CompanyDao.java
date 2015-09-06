package com.excel.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.excel.vo.CompanyVO;

public interface CompanyDao {
	public int getCount();
	public void insertCompany(CompanyVO companyVO);
	public int getCountByName(String name);
	public void updateField(@Param(value="name") String name,@Param(value="map") HashMap<String,Object> map);
}
