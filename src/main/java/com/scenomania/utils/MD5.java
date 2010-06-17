package com.scenomania.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


public class MD5 {
	public static Logger logger = Logger.getLogger(MD5.class);
	public static String getHash(String pass) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException", e);
			return "";
		}
		byte[] data = pass.getBytes(); 
		m.update(data,0,data.length);
		BigInteger i = new BigInteger(1,m.digest());
		return String.format("%1$032X", i).toLowerCase();
	}
}
