package com.hibernatevalidator;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.excep.BusinessException;

/**
 * 利用validator校验 (JSR-303标准)
 *
 * @author r6yuxx
 */

public class ValidatorUtil {

	/**
	 * 验证并抛出异常(针对基于xml配置)
	 *
	 * @param object
	 * @throws BusinessException
	 * @arr 配置文件对应的字节数据
	 */
	public static <T> String validator(T object, byte[] arr, Class<?>... groups) {
		String strErr = ValidatorUtil.validate(object, arr, groups);
		return strErr;
	}

	/**
	 * 验证对象
	 *
	 * @param object
	 * @param groups
	 * @return
	 */
	public static <T> String validate(T object, byte[] arr, Class<?>... groups) {

		Configuration<?> configuration = Validation.byDefaultProvider().configure();
		if (arr != null) {

			configuration.addMapping(new ByteArrayInputStream(arr));
		}
		ValidatorFactory factory = configuration.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);

		StringBuilder str = new StringBuilder();
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			for (Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator(); iterator.hasNext();) {
				ConstraintViolation<Object> cv = iterator.next();
				String message = cv.getMessage();
				str.append(cv.getPropertyPath().toString()).append(":").append(message).append("\n");
			}
		}
		return str.toString();
	}

}
