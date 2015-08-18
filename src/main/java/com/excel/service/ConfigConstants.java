package com.excel.service;

import java.util.HashMap;
import java.util.List;

import com.excel.vo.CityVO;
import com.excel.vo.SysDicVO;

public class ConfigConstants {
	public static HashMap<String, CityVO> CITYMAP = new HashMap<String, CityVO>();
	public static HashMap<String, SysDicVO> SYSDICMAP = new HashMap<String, SysDicVO>();

	public static void LoadCity(List<CityVO> CITY_LIST) {
		for (CityVO cityVO : CITY_LIST) {
			CITYMAP.put(cityVO.getName(), cityVO);
		}
	}

	public static void LoadIndustry(List<SysDicVO> INDUSTRY_LIST) {
		for (SysDicVO sysDicVO : INDUSTRY_LIST) {
			System.out.println(sysDicVO.getScreen_name());
			SYSDICMAP.put(sysDicVO.getScreen_name(), sysDicVO);
		}
	}

	public static int getCityID(String str) {
		try {
			
			CityVO cityVO = CITYMAP.get(str);
			
			if(cityVO == null) {
				for(String tmp:CITYMAP.keySet()) {
					if(tmp.startsWith(str)) {
						return CITYMAP.get(tmp).getId();
					}
				}
			} else {
				return cityVO.getId();
			}
			
		} catch (Exception e) {
//			System.out.println("not found City :" + str);
		}

		return 1;
	}

	public static int getIndustryID(String str) {
		try {
			return SYSDICMAP.get(str).getValue();
		} catch (Exception e) {
//			System.out.println("not found IndustryId :" + str);
		}

		return 99;
	}
}
