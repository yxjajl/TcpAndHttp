package com.hibernatevalidator.subcar;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.hibernatevalidator.Agroup;
import com.hibernatevalidator.Car;

import lombok.Data;

@Data
public class RentalCar {// extends Car {

	private String rented;
	
	private BigDecimal prem;

	private Car car1;

	/**
	 * car2与car1 相当，可以采用@ConvertGroup 来转换校验规则,这样car1用的是default,而car2则用的是Agroup
	 */
	@Valid
	@ConvertGroup(from = Default.class, to = Agroup.class)
	private Car car2;

	public RentalCar(String manufacturer, String licencePlate, int seatCount) {
		car1 = new Car(manufacturer, licencePlate, seatCount);
		car2 = new Car(manufacturer, licencePlate + licencePlate, seatCount);
		System.out.println("car1 =" + car1.getLicensePlate());
		System.out.println("car2 =" + car2.getLicensePlate());
	}
}