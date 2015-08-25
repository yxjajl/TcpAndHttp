package com.test.csv;

public class CityRes extends BaseRes<BaseCityInfo> {
	public static final String Path = filePath + "csv/cfg_worldmap.csv";

	private CityRes() {
		classz = BaseCityInfo.class;
	}

	private static CityRes INSTANCE = new CityRes();

	public static CityRes getInstance() {
		return INSTANCE;
	}

	public void otherInit() {
		System.out.println("CityRes.CityRes.otherInit");
	}

	public static void main(String[] args) throws Exception {
		CityRes.getInstance().loadFile(CityRes.Path);
		for (BaseCityInfo baseCityInfo : CityRes.getInstance().getDataList()) {
			System.out.println(baseCityInfo.getId() + "," + baseCityInfo.getLevel() + "," + baseCityInfo.getOpen_taskid());
		}
	}
}
