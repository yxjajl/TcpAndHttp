package com.groovy.ailipaytest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

public class PayAccManager {
	private static PayAccManager INSTANCE = new PayAccManager();

	private Map<String, Long> lastUptimeMap = new HashMap<String, Long>();
	private Map<String, Object> objMap = new HashMap<String, Object>();

	private PayAccManager() {
	}

	public static PayAccManager getInstance() {
		return INSTANCE;
	}

	private IAccManager loadAccManager() {
		try {
			String fileName = "classpath:config/AccountManager.groovy";
			File sFile = ResourceUtils.getFile(fileName);
			if (isNew(fileName, sFile)) {
				Object obj = GroovyUtil.loadGroovyFile(sFile);
				objMap.put(fileName, obj);
				lastUptimeMap.put(fileName, sFile.lastModified());
				return (IAccManager) obj;
			}

			return (IAccManager) objMap.get(fileName);
		} catch (Exception e) {
		}
		return null;
	}

	private boolean isNew(String fileName, File file) {
		Long lastUpdateTime = lastUptimeMap.get(fileName);
		if (lastUpdateTime == null) {
			return true;
		}

		// 文件被修改
		if (file.lastModified() != lastUpdateTime.longValue()) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
	}
}
