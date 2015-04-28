package com.superigno.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encryptor {
	
	private static final String ALGO = "PBEWITHSHA256AND128BITAES-CBC-BC";
	
	public String encrypt(String key, String text) throws Exception {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setAlgorithm(ALGO);
		encryptor.setPassword(key);
		return encryptor.encrypt(text);
	}
	
	public String decrypt(String key, String encryptedText) throws Exception {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setAlgorithm(ALGO);
		encryptor.setPassword(key);
		return encryptor.decrypt(encryptedText);
	}
}
