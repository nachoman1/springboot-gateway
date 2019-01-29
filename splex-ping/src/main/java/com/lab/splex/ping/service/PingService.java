package com.lab.splex.ping.service;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;


@Service
@EnableBinding(Source.class)
public class PingService {

	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	Source source;
	
	@Value ("ping.service.intervalInMillis:1000")
	String intervalInMillis;
	
    public void knock(final String beat) {
    	    
        logger.info("Sending heartbeat {}", beat);
        
        source.output()
        	.send(MessageBuilder
        			.withPayload(System.currentTimeMillis() + ":" + beat)
        			.build()
        			);

    }
    
    @StreamEmitter
    @Output(Source.OUTPUT)
    public Flux<String> emit() {
      return Flux.interval(
    		  Duration.ofMillis( Long.parseLong( intervalInMillis ) ) )
              .map(l -> "knocking @ " + System.currentTimeMillis());
    }   
    
}