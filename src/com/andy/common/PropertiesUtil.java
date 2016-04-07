package com.andy.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * ��������ļ�������
 * 
 * @author Andy
 */
public final class PropertiesUtil {
	
	private static final Logger logger = LogManager.getLogger(PropertiesUtil.class);
	
	private static final Resource resource = new ClassPathResource("/tafang.properties");
	public static Properties properties = new Properties();
	
	static {
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
			properties.forEach((key,value)->{
				logger.info(key + "\t" + value);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final String getString(String key){
		return properties.getProperty(key);
	}
	
	public static final int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	public static final boolean getBoolean(String key){
		return Boolean.parseBoolean(getString(key));
	}
	
	/**
	 * �Ƿ���Կ���
	 * 	
	 * @return		true:�����׶�,false:��ʽ����
	 */
	public static final boolean isDebug(){
		return getBoolean("isDebug");
	}

}
