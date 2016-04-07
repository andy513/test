package com.andy.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import com.andy.proto.Andys.Param;
import com.andy.server.core.MQSend;

public class AndyServerHandler extends ChannelHandlerAdapter {
	
	ExecutorService es = Executors.newScheduledThreadPool(50);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MQSend.send((Param) msg);
	}

	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		super.deregister(ctx, promise);
	}
	
	

}
