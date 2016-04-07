package com.andy.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringBeans {
	
	public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static final <T> T getBean(Class<T> cls){
		return context.getBean(cls);
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> T getBean(String string){
		return (T) context.getBean(string);
	}
	
}
