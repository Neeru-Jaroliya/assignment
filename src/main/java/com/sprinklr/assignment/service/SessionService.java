package com.sprinklr.assignment.service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinklr.assignment.config.Configurations;
import com.sprinklr.assignment.dao.TokenRepository;
import com.sprinklr.assignment.loginenum.UserAction;
import com.sprinklr.assignment.model.Token;
import com.sprinklr.assignment.model.User;

@Service
public class SessionService {

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	DataLoggerService dataLoggerService;

	private Token token;

	private static final Logger log = LoggerFactory.getLogger(SessionService.class);

	public String generateAuthenticationToken(User user) {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String tokenValue = bytes.toString() + "-" + user.getUserId();
		log.info("TokenValue: " + tokenValue);
		token = new Token();
		token.setData(tokenValue);
		token.setCreatedAt(new Date());
		tokenRepository.save(token);
		return tokenValue;
	}

	public int isSessionAlive(String tokenValue) {
		Token token = tokenRepository.findOne(tokenValue);
		System.out.println(" token from DB " + token + " ");
		if (token != null) {
			Date tokenDate = token.getCreatedAt();
			Date currentTime = new Date();
			long diff = (currentTime.getTime() - tokenDate.getTime());
			log.info("diff: " + diff + " tokenDate.getTime(): " + tokenDate.getTime() + " currentTime.getTime(): "
					+ currentTime.getTime());
			if (diff > Configurations.SESSION_EXPIRY_TIME) {
				System.out.println(" Token Expired " + diff);
				return 0;
			} else {
				return 1;
			}
		}
		return -1;
	}

	public String destroySession(String tokenValue) {
		String userId = tokenValue.split("-")[1];
		Token token = tokenRepository.findOne(tokenValue);
		log.info("Deleting token " + token.toString());
		tokenRepository.delete(token);
		dataLoggerService.logData(token.getData(), UserAction.SESSION_EXPIRED.name());
		return userId;
	}

	public void destroySession() {
		log.info("distroySession");
		Long currentTime = new Date().getTime();
		Date dateToCheck = new Date(currentTime - Configurations.SESSION_EXPIRY_TIME);
		List<Token> tokenList = tokenRepository.findByCreatedAtLessThanEqual(dateToCheck);
		log.info("Size of tokenList about to expire" + tokenList.size());
		for (Token token : tokenList) {
			dataLoggerService.logData(token.getData(), UserAction.SESSION_EXPIRED.name());
		}
		tokenRepository.deleteByCreatedAtLessThanEqual(dateToCheck);

	}

}
