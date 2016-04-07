package com.andy.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author Andy
 * 
 * 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键
 * “dog”，则只有被标记为“dog”的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog
 *
 */
public class DirectRabbitMQ {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		factory.setUri("amqp://andy:andy@127.0.0.1:5672/");
		Channel channel = connection.createChannel();
		String message = "Hello World!";
		String durable = "durable";
		channel.exchangeDeclare(DirectServer.QUEUE_NAME, "direct");
		channel.basicPublish(DirectServer.QUEUE_NAME, durable, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}

}
