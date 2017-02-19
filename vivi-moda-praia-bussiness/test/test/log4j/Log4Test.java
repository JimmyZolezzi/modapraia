package test.log4j;

import org.apache.log4j.Logger;

public class Log4Test {
	
	private static Logger logger =  Logger.getLogger(Log4Test.class);
	
	public static void main(String[] args) {
		logger.info("teste");
	}

}
