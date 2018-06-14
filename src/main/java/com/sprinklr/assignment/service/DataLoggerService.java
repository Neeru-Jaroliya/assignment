package com.sprinklr.assignment.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sprinklr.assignment.config.Configurations;
import com.sprinklr.assignment.dao.LoginDataRepository;
import com.sprinklr.assignment.dao.UserRepository;
import com.sprinklr.assignment.model.LoginData;
import com.sprinklr.assignment.model.User;
import com.sprinklr.assignment.util.AssignmentUtil;

@Service
public class DataLoggerService {
	
	@Autowired
	private LoginDataRepository loginDataRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private LoginData loginData;
	
	Pageable limit = new PageRequest(0, Configurations.TRAIL_LIMIT);
	
	private static final Logger log = LoggerFactory.getLogger(DataLoggerService.class);
	
	public void logData(Long userId, String action ){
		setLoginData(userId, action);
		loginDataRepository.save(loginData);
		
	}
	
	public void logData(String tokenValue, String action ){
		Long userId = AssignmentUtil.extractUserIdFromToken(tokenValue);
		setLoginData(userId, action);
		loginDataRepository.save(loginData);
		
	}
	
	public List<LoginData> getListOfTrail(String email){
		User user = userRepository.findByEmail(email);
		return loginDataRepository.findByUserId(user.getUserId(), limit);
	}
	
	private void setLoginData(Long userId, String action){
		loginData = new LoginData();
		loginData.setUserId(userId);
		loginData.setActionPerformed(action);
		loginData.setCreatedAt(new Date());
		log.info(" LoginData "+loginData.toString());
	}

}
