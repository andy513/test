package com.andy.proto;

import com.andy.proto.Andys.RequestParamProto;
import com.google.protobuf.InvalidProtocolBufferException;

public class Test {
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		Andys.Param.Builder param = Andys.Param.newBuilder();
		Andys.RequestParamProto.Builder builder = Andys.RequestParamProto.newBuilder();
		builder.setCls("cls");
		builder.setMth("mth");
		builder.setType("12");
		builder.setParam("param");
		RequestParamProto requestParam = builder.build();
		param.setId(1);
		param.setBytes(requestParam.toByteString());
		System.out.println(param.build());
		Andys.RequestParamProto.Builder new_builder = Andys.RequestParamProto.newBuilder();
		new_builder.mergeFrom(param.getBytes());
		System.out.println(new_builder);
	}

}
