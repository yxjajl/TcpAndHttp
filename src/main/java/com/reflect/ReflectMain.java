package com.reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.util.ReflectUtil;

//java8 :: 双冒号用法
public class ReflectMain {
	public static void main(String[] args) throws Exception {
		// PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(ReflectMain.class);
		// testPrintBean();
		// testBeanToMap();

		// 4
//		Runnable action = ReflectMain::testBeanToMap;// 双冒号取方法
//		testMethod(action);

		ReflectMain rm = new ReflectMain();

		Supplier<ReflectMain> sup = ReflectMain::new;
		Consumer<Integer> consumer = ReflectMain::ttm1;
		Function<Integer, Integer> ff = ReflectMain::ttm2;
		Predicate<Integer> pre = rm::ttm3;

		// BiConsumer<R, Integer> bi = ReflectMain::ttm2;

		// collect(StringBuilder::new, StringBuilder::append);
		rm.collect(StringBuilder::new, StringBuilder::append);
		rm.ttm4(ArrayList<Integer>::add); // 特殊的集合add,append方法才可以转成BigConsumer
		rm.ttm5(StrTest::append); // 验证自己的想法，果然如此
		rm.ttm5(rm::comsumer);

		consumer.accept(3);
		ff.apply(3);
		pre.test(3);
	}

	public <R> void collect(Supplier<R> supplier, BiConsumer<R, String> accumulatorr) {
		R r = supplier.get();
		accumulatorr.accept(r, "a");
		accumulatorr.accept(r, "b");
		System.out.println("dd" + r);
	}

	public void ttm4(BiConsumer<ArrayList<Integer>, Integer> acc) {
		System.out.println("ttm4");
	}

	public <R> void ttm5(BiConsumer<R, String> acc) {
		System.out.println("ttm5");
	}

	public void comsumer(String a, String b) {

	}

	public Integer append(Integer x) {
		return 0;
	}

	public static void ttm1(Integer a) {
		System.out.println("ttm1");
	};

	public static Integer ttm2(Integer a) {
		System.out.println("ttm2");
		return 1;
	}

	public boolean ttm3(Integer a) {
		System.out.println("ttm3");
		return true;
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

class StrTest {
	StringBuilder value = new StringBuilder();

	public StrTest append(String str) {
		value.append(str);
		return this;
	}
}
