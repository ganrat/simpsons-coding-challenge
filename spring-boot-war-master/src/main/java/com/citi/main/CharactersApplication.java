package com.citi.main;


import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.citi.service"})
public class CharactersApplication {

	private static Logger logger = Logger.getLogger(CharactersApplication.class);
	public static void main(String[] args) {
		
		logger.info("----Executing somplewebTomcatApp method----");
		//SpringApplication.run(CharactersApplication.class, args);
		
		ApplicationContext applicationContext =  SpringApplication.run(CharactersApplication.class, args);
		
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		
		for(String beanName : beanNames ) {
			logger.info("::::::Bean Names::::::");
			logger.info(beanName);
		}
	}
}