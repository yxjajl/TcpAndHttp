package com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QueueProducer {

	public final static String QUEUE_NAME = "hellorick";

	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("member");
		factory.setPassword("Aa123456");
		// 设置 RabbitMQ 地址
		factory.setHost("10.142.142.186");
		factory.setVirtualHost("member-vhost");
		// 建立到代理服务器到连接
		Connection conn = factory.newConnection();
		// 获得信道
		Channel channel = conn.createChannel();

		channel.queueDeclare(QUEUE_NAME, true, false, false, null);

		channel.basicPublish("", "hello456", null, "ilvodfdf".getBytes());
		channel.close();
        conn.close();
	}
}
