package com.sprinklr.assignment.controller;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprinklr.assignment.dao.UserRepository;
import com.sprinklr.assignment.loginenum.UserAction;
import com.sprinklr.assignment.model.LoginData;
import com.sprinklr.assignment.model.TokenRequest;
import com.sprinklr.assignment.model.User;
import com.sprinklr.assignment.service.DataLoggerService;
import com.sprinklr.assignment.service.SessionService;
import com.sprinklr.assignment.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	DataSource dataSource;

	@Autowired
	SessionService sessionService;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DataLoggerService dataLoggerService;
	
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/create-user")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> createUser(@RequestBody User user) {
		log.info("*********  " + user.toString() + " *********");
		if (userService.createUser(user)) {
			return new ResponseEntity<String>("User Created Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Creation Failed",HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/login")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> login(@RequestBody User user) {
		log.info("*********  " + user.toString() + " *********");
		User dbUser = userService.loginUser(user);
		if (dbUser != null) {
			dataLoggerService.logData(dbUser.getUserId(), UserAction.LOGGED_IN.name());
			String token = sessionService.generateAuthenticationToken(dbUser);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/session-alive")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> sessionAlive(@RequestParam("token") String token) { // change to GET
		String tokenValue = token;
		log.info("*********  login" + tokenValue + " *********");
		int value = sessionService.isSessionAlive(tokenValue);
		if (value == 1) {
			return new ResponseEntity<String>("Session Alive", HttpStatus.OK);
		}else if(value == 0){
			dataLoggerService.logData(tokenValue, UserAction.LOGGED_OUT.name()); // check this
		}
		return new ResponseEntity<String>("Session expired",HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/logout")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> logout(@RequestBody TokenRequest request) {
		String tokenValue = request.getToken();
		log.info("********* logout " + tokenValue + " *********");
		String userId = sessionService.destroySession(tokenValue);
		dataLoggerService.logData(Long.parseLong(userId), UserAction.LOGGED_OUT.name());
		return new ResponseEntity<String>("User logged out successfully",HttpStatus.OK);
	}
	
	@RequestMapping("/login-trail")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<LoginData>> getLoginTrail(@RequestParam("email") String email) {
		List<LoginData> loginDataList = dataLoggerService.getListOfTrail(email); 
		for (LoginData loginData : loginDataList) {
			log.info(" loginData: " + loginData.toString());
		}
		return new ResponseEntity<List<LoginData>>(loginDataList, HttpStatus.OK);
	}

}
