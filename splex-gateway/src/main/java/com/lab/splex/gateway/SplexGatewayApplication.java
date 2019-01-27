package com.lab.splex.gateway;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import com.lab.splex.gateway.service.TrackerService;

@SpringBootApplication
@EnableBinding(Processor.class)
public class SplexGatewayApplication {

	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired 
	TrackerService tracker;
	
	public static void main(String[] args) {
		SpringApplication.run(SplexGatewayApplication.class, args);
	}
	
	  @StreamListener(Processor.INPUT)
	  @SendTo(Processor.OUTPUT)
	  public String processEvent(String event) {
	      logger.info("----> " + event);
	      return tracker.stamp(event);
	  }	
	  
	  @ServiceActivator(inputChannel = Processor.INPUT + ".demosink.errors") //channel name 'input.demosink.errors'
	  public void error(Message<?> message) {
		  logger.error("Handling ERROR: " + message);
	  }	  
}
