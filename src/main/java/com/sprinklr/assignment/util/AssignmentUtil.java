package com.sprinklr.assignment.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AssignmentUtil {
	
	private static final Logger log = LoggerFactory.getLogger(AssignmentUtil.class);

	// private static String salt = "sprinklr";
	private static MessageDigest md;

	
	public static boolean validEmail(String email) {
		Pattern pattern = Pattern.compile("^.+@.+\\..+$"); // can be replaced
															// with a better
															// logic
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validPassword(String password) {
		if (password == null) {
			return false;
		}
		if (password.length() < 8) {
			return false;
		}
		return true;
	}
	
	public static String encodeString(String str) {
		try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = str.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	    	log.info("NoSuchAlgorithmException");
	        ex.printStackTrace();
	    }
	        return null;
	}
	
	public static Long extractUserIdFromToken(String tokenValue) {
	    if(tokenValue == null){
			throw new RuntimeException("Invalid Token Value");
		}else if(tokenValue.split("-").length <2){
			throw new RuntimeException("Invalid Token Value");
		} 
		return Long.parseLong(tokenValue.split("-")[1]);
	}
}
