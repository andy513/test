package com.andy.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSONObject;
import com.andy.proto.Andys;
import com.andy.proto.Andys.RequestParamProto;

public class AndyClientHandler extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 50; i++) {
			ctx.writeAndFlush(extracted(1));
			Thread.sleep(5 * 1000);
		}
		System.out.println("Game Over!");
	}

	private Andys.Param extracted(final int i) {
		Andys.Param.Builder builder = Andys.Param.newBuilder();
		builder.setId(1);
		/*builder.setCls("cls");
		builder.setMth("mth" + i);
		builder.setType("andy");*/
		JSONObject json = new JSONObject();
		json.put("uname", "andy");
		json.put("pwd", "andy");
		json.put("age", 12);
		builder.setBytes(RequestParamProto.newBuilder().setCls("t").setMth("m").setType("a").setParam(json.toJSONString()).build().toByteString());
//		builder.setParam(json.toJSONString());
		Andys.Param param = builder.build();
		return param;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}
