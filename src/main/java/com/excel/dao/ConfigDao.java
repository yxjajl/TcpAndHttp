package com.excel.dao;

import java.util.List;

import com.excel.vo.CityVO;
import com.excel.vo.SysDicVO;

public interface ConfigDao {
	public List<CityVO> getCity();

	public List<SysDicVO> getIndustry();
	
	public List<Integer> getIndustry2();
	
	public List<Integer> getCategory2(int industry);
}
