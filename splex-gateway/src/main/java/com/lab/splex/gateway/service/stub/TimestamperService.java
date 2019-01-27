package com.lab.splex.gateway.service.stub;

import org.springframework.stereotype.Component;

import com.lab.splex.gateway.service.TrackerService;

@Component
public class TimestamperService implements TrackerService {

	@Override
	public String stamp(String request) {

		return System.currentTimeMillis() + ":" + request;
	} 

}
