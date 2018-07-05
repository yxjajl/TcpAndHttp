package com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicProducer {
	public static final String QUEUE_NAME = "queue.member.weixin.test3";

	public static void main(String[] args) throws IOException, TimeoutException {

		String routingKey = "hola";
		// 声明交换器
		String exchangeName = "hello-exchange";

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

		channel.exchangeDeclare(exchangeName, "direct", true);
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, exchangeName, routingKey);

		// 发布消息
		byte[] messageBodyBytes = "abcdfe-message ".getBytes();
		channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

		channel.close();
		conn.close();
	}
}
