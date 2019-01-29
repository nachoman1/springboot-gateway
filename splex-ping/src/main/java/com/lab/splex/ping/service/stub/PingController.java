package com.lab.splex.ping.service.stub;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.splex.ping.service.PingService; 

@RestController
public class PingController {

    @Autowired
	PingService pingService;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void ping(
    		@RequestParam("message") String message) {

        pingService.knock(message);

    }

}