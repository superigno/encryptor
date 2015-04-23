package com.superigno.util;

import org.jasypt.util.text.BasicTextEncryptor;

public class BasicEncryptor {
	
	public String encrypt(String key, String text) throws Exception {
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(key);
		return encryptor.encrypt(text);
	}
	
	public String decrypt(String key, String encryptedText) throws Exception {
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(key);
		return encryptor.decrypt(encryptedText);
	}
}
