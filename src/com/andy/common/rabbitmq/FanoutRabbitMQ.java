package com.andy.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Andy
 * 
 *         不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。很像子网广播，
 *         每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
 * 
 */
public class FanoutRabbitMQ {

	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		factory.setUri("amqp://andy:andy@127.0.0.1:5672/");
		Channel channel = connection.createChannel();
		// 创建一个交换机类型,名字叫logs
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String body = "hello world!";
		channel.basicPublish(EXCHANGE_NAME, "", null, body.getBytes());
		channel.close();
		connection.close();
	}

}
