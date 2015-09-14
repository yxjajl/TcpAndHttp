package com.instrumenta;

import java.lang.instrument.Instrumentation;

public class LoadedAgent {

	public static void agentmain(String args, Instrumentation inst) {
		Class[] classes = inst.getAllLoadedClasses();
		for (Class cls : classes) {
			System.out.println(cls.getName());
		}
	}
}