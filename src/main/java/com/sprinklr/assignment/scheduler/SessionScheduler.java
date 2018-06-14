package com.sprinklr.assignment.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sprinklr.assignment.service.SessionService;

@Component
public class SessionScheduler {
	
	@Autowired
	private SessionService sessionService;
	
    private static final Logger log = LoggerFactory.getLogger(SessionScheduler.class);

	
	@Scheduled(fixedRate = 60000)
    public void destroyInvalidSessions() {
		sessionService.destroySession();
        log.info("Scheduler triggered");
        
    }

}
