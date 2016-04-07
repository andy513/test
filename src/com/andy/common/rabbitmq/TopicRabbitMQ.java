package com.andy.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Andy
 *
 *	将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”
 *  能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”
 *
 */
public class TopicRabbitMQ {
	
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		factory.setUri("amqp://andy:andy@127.0.0.1:5672/");
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String message = channel.queueDeclare().getQueue();
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
	}

}
