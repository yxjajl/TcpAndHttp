package com.zk;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class WatchSubNode {
	private static final String root = "/root";
	ZooKeeper zk = null;

	private Watcher getWatcher(final String msg) {
		return new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(msg + "\t" + event.toString());
			}
		};
	}

	public WatchSubNode(String address) throws IOException, KeeperException, InterruptedException {

		zk = new ZooKeeper(address, 3000, getWatcher("create")); // 在创建ZooKeeper时第三个参数负责设置该类的默认构造函数
		if (zk.exists(root, getWatcher("parent watch")) == null) {
			zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		List<String> childList = zk.getChildren(root, getWatcher("Child watch"));
	}

	public void addChild() throws KeeperException, InterruptedException {
		zk.create(root + "/subnode-", "subnode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	public static void main(String[] args) throws Exception {
		WatchSubNode wsn = new WatchSubNode("127.0.0.1:2181");
		wsn.addChild();
	}
}
