package com.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class QueueConsumer {
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

		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
				// super.handleDelivery(consumerTag, envelope, properties, body);
				String message = new String(body, "UTF-8");
				System.out.println("receiver" + message);
			}

		};

		channel.basicConsume(QueueProducer.QUEUE_NAME, consumer);
	}
}
