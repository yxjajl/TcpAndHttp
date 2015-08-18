package com.excel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.dao.CompanyDao;
import com.excel.dao.ConfigDao;
import com.excel.vo.CompanyVO;

@Service("companyService")
public class CompanyServiceImp implements CompanyService {
	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private ConfigDao configDao;

	public int getCount() {
		return companyDao.getCount();
	}

	public void insertCompany(CompanyVO companyVO) {
		companyDao.insertCompany(companyVO);
	}

	public void initConfig() {
		ConfigConstants.LoadCity(configDao.getCity());
		ConfigConstants.LoadIndustry(configDao.getIndustry());
	}

	public boolean exist(String name) {
		return companyDao.getCountByName(name) > 0;
	}
}
