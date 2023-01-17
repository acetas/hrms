package com.kodio.hrms.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	public static String encryptPass(String password) {

		String generatedPassword = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] messageDigest = md.digest(password.getBytes());

			BigInteger bigInt = new BigInteger(1, messageDigest);

			generatedPassword = bigInt.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return generatedPassword;

	}

}
