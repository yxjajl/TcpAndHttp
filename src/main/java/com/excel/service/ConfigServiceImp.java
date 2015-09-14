package com.excel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.dao.ConfigDao;

@Service("configService")
public class ConfigServiceImp implements ConfigService {
	@Autowired
	private ConfigDao configDao;

	public List<Integer> getIndustry2() {
		return configDao.getIndustry2();
	}

	public List<Integer> getCategory2(int industry) {
		return configDao.getCategory2(industry);
	}
}
