package com.zk;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

//获得锁：
//1.创建根节点"/root"
//2.在根节点下新建子节点"/root/c-xxxxxx"，SEQUENTIAL模式
//3.对根节点调用getChildren()，如果第2步创建的节点是所有子节点中序号最小的，则获得锁；否则进入第4步
//4.在序号最小的子节点上调用exists()，当序号最小的子节点被删除后返回第3步
//
//释放锁：
//删除自己创建的子节点即可

public class Locks implements Watcher {

	static ZooKeeper zk = null;
	static Integer mutex = null;
	String name = null;
	String path = null;

	@Override
	synchronized public void process(WatchedEvent event) {
		System.out.println("Watcher.process == " + event.toString());
		synchronized (mutex) {
			System.out.println(mutex);
			mutex.notify();
		}
	}

	Locks(String address) {
		try {
			zk = new ZooKeeper(address, 2000, this);
			if (zk.exists("/lock", true) == null) {
				zk.create("/lock", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			mutex = new Integer(-1);
			name = new String(InetAddress.getLocalHost().getCanonicalHostName().toString());

		} catch (IOException e) {
			zk = null;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int minSeq(List<String> list) {
		int min = getIndex(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			int vv = getIndex(list.get(i));
			if (min > vv)
				min = vv;
		}
		return min;
	}

	private int getIndex(String str) {
		int n = str.lastIndexOf("-");
		String num = str.substring(n + 1);
		return Integer.parseInt(num);
	}

	private String getHead(String str) {
		int n = str.lastIndexOf("-");
		String head = str.substring(0, n);
		return head;
	}

	boolean getLock() throws KeeperException, InterruptedException {
		// create方法返回新建的节点的完整路径
		path = zk.create("/lock/" + name + "-", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		int min;
		int currentIndex = getIndex(path);
		System.out.println("currentIndex = " + currentIndex);
		while (true) {
			synchronized (mutex) {
				List<String> list = zk.getChildren("/lock", true);
				min = minSeq(list);
				// 如果刚建的节点是根节点的所有子节点中序号最小的，则获得了锁，可以返回true
				if (min == currentIndex) {
					return true;
				} else {
					mutex.wait(); // 等待事件（新建节点或删除节点）发生
					while (true) {
						Stat s = zk.exists("/lock/" + name + "-" + min, true); // 查看序号最小的子节点还在不在
						if (s != null) // 如果还在，则继续等待事件发生
							mutex.wait();
						else
							// 如果不在，则跳外层循环中，查看新的最小序号的子节点是谁
							break;
					}
				}
			}
		}
	}

	boolean releaseLock() throws KeeperException, InterruptedException {
		System.out.println("================releaseLock==========================");
		if (path != null) {
			System.out.println("================releaseLock=====delete=====================");
			zk.delete(path, -1);
			path = null;
		}
		return true;
	}

	public static void main(String[] args) throws KeeperException, InterruptedException {
		Locks lock1 = new Locks("localhost:2181");
		if (lock1.getLock()) {
			System.out.println("T1 Get lock at " + System.currentTimeMillis());
			for (int i = 0; i < 3; ++i)
				Thread.sleep(5000);
			lock1.releaseLock();
		}
//		Locks lock2 = new Locks("localhost:2181");
//		if (lock2.getLock()) {
//			System.out.println("T2 Get lock at " + System.currentTimeMillis());
//			lock2.releaseLock();
//		}
	}

}