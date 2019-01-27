package com.lab.splex.gateway;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SplexGatewayApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MessageFlowSuite {
 
    @Autowired
    private Processor pipe;
 
    @Autowired
    private MessageCollector messageCollector;
 
    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        String messageTest = "This is my message";
		pipe.input()
          .send(MessageBuilder.withPayload(new String(messageTest))
          .build());
 
        Object payload = messageCollector.forChannel(pipe.output())
          .poll()
          .getPayload();
 
        assertThat(payload.toString(), matchesPattern("[0-9]*:"+ messageTest));
    }
}