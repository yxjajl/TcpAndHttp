package com.instrumenta;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Premain {
	public static void premain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
		System.out.println("===Premain===");
		//inst.addTransformer(new Transformer());
	}
}