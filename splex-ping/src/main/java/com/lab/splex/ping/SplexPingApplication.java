package com.lab.splex.ping;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SplexPingApplication {

	Logger logger = LogManager.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SplexPingApplication.class, args);
	}
	
}
