package com.andy.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AndyTest {

//	public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	private static Logger log = LogManager.getLogger(AndyTest.class);
	private static Logger l = LogManager.getLogger(AndyTest.class.getName());
	private static Logger rl = LogManager.getRootLogger();

	public static void main(String[] args) {
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		logger.fatal("fatal");

		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");

		l.trace("trace");
		l.debug("debug");
		l.info("info");
		l.warn("warn");
		l.error("error");
		l.fatal("fatal");

		rl.trace("trace");
		rl.debug("debug");
		rl.info("info");
		rl.warn("warn");
		rl.error("error");
		rl.fatal("fatal");
	}

}
