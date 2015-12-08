package com.annotation.springlock;

import org.springframework.stereotype.Component;

@Component
public class Bus implements VehicleInerface {

	@Override
	public void dodo() {
		System.out.println("bus");
	}

}
