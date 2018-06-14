package com.sprinklr.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinklr.assignment.dao.UserRepository;
import com.sprinklr.assignment.model.User;
import com.sprinklr.assignment.util.AssignmentUtil;

@Service
public class UserService {
	
	private User user;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	public boolean createUser(User user){
		if ( !AssignmentUtil.validPassword(user.getPassword())){
			return false;
		}
		if(getDBUserWithEmailCheck(user.getEmail()) != null){
			return false;
		}
		user.setPassword(AssignmentUtil.encodeString(user.getPassword()));
		userRepository.save(user);
		log.info(" User created successfully");
		return true;
		
		
	}
	
	public User loginUser(User user){
		User dbUser = getDBUserWithEmailCheck(user.getEmail());
		if ( dbUser == null){
			return null;
		}
		if (!checkValidPassword(user.getPassword(), dbUser.getPassword())){
			log.info("Password Validation Failed");
			return null;
		}
		log.info(" loginUser validity passed: "+user.toString());
		return dbUser;
	}
	
	private User getDBUserWithEmailCheck(String email){
		if ( !AssignmentUtil.validEmail(user.getEmail())){
			log.info("Email Validation Failed");
			return null;
		}
		User dbUser = userRepository.findByEmail(email);
		if(dbUser == null ){
			return null;
		}else{
			return dbUser;
		}
	}
	
	private boolean checkValidPassword(String userPwd, String dbUserPwd){
		if ( !AssignmentUtil.validPassword(user.getPassword())){
			log.info("Password Validation Failed");
			return false;
		}
		if( dbUserPwd.equals(AssignmentUtil.encodeString(userPwd))){
			log.info(" Password Matched");
			return true;
		}else{
			log.info("User or Password is not correct");
			return false;
		}
		
	}
	

}
