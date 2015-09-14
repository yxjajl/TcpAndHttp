package com.instrumenta;

import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class AttachTest {
	public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm = VirtualMachine.attach(args[0]);// args[0]传入的是jvm的pid号
		// vm.loadAgent("/Users/jiangbo/Workspace/code/java/javaagent/loadagent.jar");
		vm.loadAgent("F:\\workspace_aging_rejuvenate\\AgentMain\\agent.jar");

	}

}