package com.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class SelfWatcher2 {

	ZooKeeper zk = null;

	private Watcher getWatcher(final String msg) {
		return new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(msg + "\t" + event.toString());
			}
		};
	}

	SelfWatcher2(String address) {
		try {
			zk = new ZooKeeper(address, 3000, getWatcher("CREATE")); // 在创建ZooKeeper时第三个参数负责设置该类的默认构造函数
			zk.create("/root", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (IOException e) {
			e.printStackTrace();
			zk = null;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void setWatcher() {
		try {
			Stat s = zk.exists("/root", getWatcher("EXISTS"));
			if (s != null) {
				zk.getData("/root", getWatcher("GETDATA"), s);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void trigeWatcher() {
		try {
			Stat s = zk.exists("/root", true); // 此处不设置watcher
			zk.setData("/root", "a".getBytes(), s.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void deleteRoot() {
		try {
			zk.delete("/root", -1);
			// 触发data watches和children watches
			Stat s = zk.exists("/root", getWatcher("EXISTS"));
			if (s != null) {
				zk.getData("/root", getWatcher("GETDATA"), s);
				zk.getChildren("/root", getWatcher("LISTCHILDREN"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (zk != null)
			try {
				zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		SelfWatcher2 inst = new SelfWatcher2("127.0.0.1:2181");
		inst.setWatcher();
		inst.trigeWatcher();
		inst.deleteRoot();
		inst.disconnect();
	}

}