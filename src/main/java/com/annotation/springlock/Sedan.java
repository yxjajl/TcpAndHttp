package com.annotation.springlock;

import org.springframework.stereotype.Component;

@Component
@Car
public class Sedan implements VehicleInerface {
	@Override
	public void dodo() {
		System.out.println("dodo:Sedan");
	}

}
