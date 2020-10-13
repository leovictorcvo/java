package br.com.leovictorcvo.fileuploader.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class HashService {

	public static String HashSha3Hex(String originalString) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
		final byte[] hashbytes = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(hashbytes);
	}
	
	public static String randomHex() {
		Random rd = new Random();
	      byte[] arr = new byte[10];
	      rd.nextBytes(arr);
	      return bytesToHex(arr);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
