package com.zk;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class CreateNode {
	private static final int TIMEOUT = 3000;
	private static final String hostUrl = "localhost:2181";// "10.10.11.1:2181";

	public static void main(String[] args) throws IOException {
		ZooKeeper zkp = new ZooKeeper(hostUrl, TIMEOUT, null);
		String strNode = "/ricknode1";
		byte[] bNodeData = "data1".getBytes();
		try {
			// 创建一个EPHEMERAL类型的节点，会话关闭后它会自动被删除
			// PERSISTENT：创建后只要不删就永久存在
			// EPHEMERAL：会话结束年结点自动被删除，EPHEMERAL结点不允许有子节点
			// SEQUENTIAL：节点名末尾会自动追加一个10位数的单调递增的序号，同一个节点的所有子节点序号是单调递增的
			// PERSISTENT_SEQUENTIAL：结合PERSISTENT和SEQUENTIAL
			// EPHEMERAL_SEQUENTIAL：结合EPHEMERAL和SEQUENTIAL

			zkp.create(strNode, bNodeData, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			if (zkp.exists(strNode, false) != null) {
				System.out.println(strNode + " exists now.");
			}
			try {
				// 当节点名已存在时再去创建它会抛出KeeperException(即使本次的ACL、CreateMode和上次的不一样)
				zkp.create(strNode, bNodeData, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			} catch (KeeperException e) {
				System.out.println("KeeperException caught:" + e.getMessage());
			}

			// 关闭会话
			zkp.close();

			zkp = new ZooKeeper(hostUrl, TIMEOUT, null);
			// 重新建立会话后node1已经不存在了
			if (zkp.exists(strNode, false) == null) {
				System.out.println(strNode + " dosn't exists now.");
			}
			strNode = "/node-";
			bNodeData = "same data".getBytes();
			// 创建SEQUENTIAL节点
			zkp.create(strNode, bNodeData, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			zkp.create(strNode, bNodeData, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			zkp.create(strNode, bNodeData, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			List<String> children = zkp.getChildren("/", null);
			System.out.println("Children of root node:");
			Collections.sort(children);
			for (String child : children) {
				System.out.println(child);
			}

			zkp.close();

			// zkp = new ZooKeeper(hostUrl, TIMEOUT, null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}