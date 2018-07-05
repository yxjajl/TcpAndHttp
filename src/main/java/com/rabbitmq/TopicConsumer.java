package com.rabbitmq;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("member");
		factory.setPassword("Aa123456");
		// 设置 RabbitMQ 地址
		factory.setHost("10.142.142.186");
		factory.setVirtualHost("member-vhost");
		// 建立到代理服务器到连接
		Connection conn = factory.newConnection();
		// 获得信道
		final Channel channel = conn.createChannel();
		// 声明交换器
		String exchangeName = "queue";
		channel.exchangeDeclare(exchangeName, "topic", true);
		// 声明队列
//		String queueName = channel.queueDeclare().getQueue();
		String routingKey = "#";
		String queueName = TopicProducer.QUEUE_NAME;
		// 绑定队列，通过键 hola 将队列和交换器绑定起来
		channel.queueBind(queueName, exchangeName, routingKey);

		while (true) {
			// 消费消息
			boolean autoAck = false;
			String consumerTag = "";
			channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					String routingKey = envelope.getRoutingKey();
					String contentType = properties.getContentType();
					System.out.println("消费的路由键：" + routingKey);
					System.out.println("消费的内容类型：" + contentType);
					long deliveryTag = envelope.getDeliveryTag();
					// 确认消息
					channel.basicAck(deliveryTag, false);
					System.out.println("消费的消息体内容：");
					String bodyStr = new String(body, "UTF-8");
					System.out.println(bodyStr);

				}
			});
		}
	}
}
