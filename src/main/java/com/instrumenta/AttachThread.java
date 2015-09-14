package com.instrumenta;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

// 一个运行 Attach API 的线程子类
public class AttachThread extends Thread {
	public static void main(String[] args) throws InterruptedException {
		new AttachThread("E:\\instrument2\\TestInstrument1.jar", VirtualMachine.list()).start();

	}

	private List<VirtualMachineDescriptor> listBefore;

	private String jar;

	AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
		listBefore = vms; // 记录程序启动时的 VM 集合
		jar = attachJar;
	}

	public void run() {
		VirtualMachine vm = null;
		List<VirtualMachineDescriptor> listAfter = null;
		try {
			int count = 0;
			while (true) {
				listAfter = VirtualMachine.list();
				for (VirtualMachineDescriptor vmd : listAfter) {
					if (!listBefore.contains(vmd)) {
						// 如果 VM 有增加，我们就认为是被监控的 VM 启动了
						// 这时，我们开始监控这个 VM
						vm = VirtualMachine.attach(vmd);
						break;
					}
				}
				Thread.sleep(800);
				count++;
				if (null != vm || count >= 10) {
					break;
				}
			}
			System.out.println("a:" + vm);
			vm.loadAgent(jar);
			vm.detach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
