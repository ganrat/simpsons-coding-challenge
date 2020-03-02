package com.citi.json;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.citi.json.service"})
public class JSONReaderAPIClientApplication {
	private static Logger logger = Logger.getLogger(JSONReaderAPIClientApplication.class);
	public static void main(String[] args) {
		logger.info("-------Executing JSON Reader APIClient Application");
		SpringApplication.run(JSONReaderAPIClientApplication.class, args);
	}
	
	
}