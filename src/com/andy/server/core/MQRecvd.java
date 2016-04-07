package com.andy.server.core;

import com.andy.proto.Andys;
import com.andy.proto.Andys.RequestParamProto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class MQRecvd {
	private static final String EXCHANGE_KEY = "exchange_key";
	private static final String QUEUENAME = "queueName";

	public static void main(String[] args) throws Exception {
		revd();
	}

	public static final void revd() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		factory.setAutomaticRecoveryEnabled(true);
		factory.setUri("amqp://andy:andy@192.168.0.233:5672/");
		Channel channel = connection.createChannel();
		try {
			channel.exchangeDeclare(EXCHANGE_KEY, "direct", true);
			channel.queueBind(QUEUENAME, EXCHANGE_KEY, "andy");
			QueueingConsumer consumber = new QueueingConsumer(channel);
			channel.basicConsume(QUEUENAME, false, consumber);
			while (true) {
				QueueingConsumer.Delivery deliver = consumber.nextDelivery();
				Andys.Param param = Andys.Param.parseFrom(deliver.getBody());
				int id = param.getId();
				switch(id){
				case 1:
					RequestParamProto.Builder builder = RequestParamProto.newBuilder();
					builder.mergeFrom(param.getBytes());
					System.out.println(id + "\t" + builder);
					break;
				}
				channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
			}
		} finally {
			channel.close();
			connection.close();
		}
	}
}
