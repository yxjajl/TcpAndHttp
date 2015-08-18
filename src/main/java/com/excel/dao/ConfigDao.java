package com.excel.dao;

import java.util.List;

import com.excel.vo.CityVO;
import com.excel.vo.SysDicVO;

public interface ConfigDao {
	public List<CityVO> getCity();

	public List<SysDicVO> getIndustry();
}
