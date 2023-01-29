package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyUtils {
	
	public static boolean isEmptyString(String str) {
		return str.isEmpty() || str == null;
	}
	
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(str.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
	        return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
        
	}
	
}
