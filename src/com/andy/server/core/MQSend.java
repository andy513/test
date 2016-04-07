package com.andy.server.core;

import java.io.IOException;

import com.andy.proto.Andys;
import com.andy.proto.Andys.RequestParamProto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public final class MQSend {

	private static final ConnectionFactory factory = new ConnectionFactory();
	private static final String EXCHANGE_KEY = "exchange_key";
	private static final String QUEUENAME = "queueName";

	public static void main(String[] args) throws Exception {
		/*Andys.Param.Builder builder = Andys.Param.newBuilder();
		builder.setId(1).setBytes(RequestParamProto.newBuilder().setCls("t").setMth("m").setType("a").setParam("fdafds").build().toByteString());
		while (true) {
			try {
				send(builder.build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}

	static Connection connection = null;
	static Channel channel = null;

	static {
		// 服务器断开后,自动重连
		// factory.setUri("amqp://guest:guest@127.0.0.1:5672/");
		/*factory.setHost("127.0.0.1");
		factory.setPort(5672);*/
		factory.setAutomaticRecoveryEnabled(true);
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void send(Andys.Param param) throws IOException {
		channel.exchangeDeclare(EXCHANGE_KEY, "direct", true);
		channel.queueDeclare(QUEUENAME, true, false, false, null);
		channel.queueBind(QUEUENAME, EXCHANGE_KEY, param.getId() + "");
		channel.basicPublish(EXCHANGE_KEY, param.getId() + "", MessageProperties.PERSISTENT_TEXT_PLAIN, param.toByteArray());
	}

}
