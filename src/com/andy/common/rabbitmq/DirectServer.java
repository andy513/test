package com.andy.common.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class DirectServer {

	public final static String QUEUE_NAME = "direct_logs";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		factory.setUri("amqp://andy:andy@127.0.0.1:5672/");
		Channel channel = connection.createChannel();
		//我们需要确保RabbitMQ不会丢失我们的队列，为了这样做，我们需要将它声明为持久化：
		String durable = "durable";
		channel.exchangeDeclare(QUEUE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, QUEUE_NAME, durable);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		while (true) {
			//QueueingConsumer.nextDelivery()在另一个来自服务器的消息到来之前它会一直阻塞着
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + delivery.getEnvelope().getDeliveryTag() + ":" + message + "'");
			//消息确认
//			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}

}
