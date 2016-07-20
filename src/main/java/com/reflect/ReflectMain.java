package com.reflect;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.util.ReflectUtil;

public class ReflectMain {
	public static void main(String[] args) throws Exception {
		// PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(ReflectMain.class);
		testPrintBean();
		// testBeanToMap();

		// 4
//		Runnable action = ReflectMain::testBeanToMap;// 双冒号取方法
//		testMethod(action);
	}

	public static void testPrintBean() throws Exception {
		ReflectTestVO reflectTestVO = new ReflectTestVO();
		reflectTestVO.setCard("card");
		reflectTestVO.setName("name");
		reflectTestVO.setOverStr("overstr");
		reflectTestVO.setRaid(100);
		reflectTestVO.xman = 8;
		reflectTestVO.superStr = "superstr";
		ReflectUtil.printAllField(reflectTestVO);

		ReflectUtil.setFieldsValue(reflectTestVO, "xman", 9);
		ReflectUtil.setFieldsValue(reflectTestVO, "card", "hehecard");
		ReflectUtil.setFieldsValue(reflectTestVO, "name", "hehename");
		ReflectUtil.setFieldsValue(reflectTestVO, "superStr", "hehesuperStr");
		ReflectUtil.printAllField(reflectTestVO);
	}

	public static void testMethod(Runnable action) throws Exception {
		long sum = 0;
		int maxtimes = 100;
		long max = 0L;
		long min = 99999999999L;
		Supplier<Long> supplier = () -> {
			long d1 = System.currentTimeMillis();
			action.run();
			return System.currentTimeMillis() - d1;
		};

		for (int i = 0; i < maxtimes; i++) {
			long use = supplier.get();
			if (use > max) {
				max = use;
			}
			if (use < min) {
				min = use;
			}
			sum += use;
		}
		System.out.println("avgtime:" + (sum / maxtimes));
		System.out.println("max:" + max);
		System.out.println("min:" + min);
	}

	public static void testBeanToMap() {
		ReflectTestVO reflectTestVO = new ReflectTestVO();
		reflectTestVO.setCard("card");
		reflectTestVO.setName("name");
		reflectTestVO.setOverStr("overstr");
		reflectTestVO.setRaid(100);
		reflectTestVO.xman = 8;
		reflectTestVO.superStr = "superstr";
		for (int i = 0; i < 1000000; i++) {
			try {
				// toMap(reflectTestVO);
				ReflectUtil.beanToMap(reflectTestVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Map<String, Object> toMap(ReflectTestVO reflectTestVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("superStr", reflectTestVO.superStr);
		map.put("name", reflectTestVO.getName());
		map.put("xman", reflectTestVO.xman);
		map.put("card", reflectTestVO.getCard());
		map.put("overStr", reflectTestVO.getOverStr());
		map.put("raid", reflectTestVO.getRaid());
		return map;
	}
}
