package com.hibernatevalidator;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data

public class Car {
	@NotNull()
	private String manufacturer;

	@NotNull
	@Size(min = 5, max = 14, message = "licensePlate error", groups = { Agroup.class, Bgroup.class })
	private String licensePlate;

	@Min(2)
	private int seatCount;

	@AssertTrue(message = "The car has to pass the vehicle inspection first")
	private boolean passedVehicleInspection;

	private String driver;

	public Car(String manufacturer, String licencePlate, int seatCount) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seatCount = seatCount;
	}
}