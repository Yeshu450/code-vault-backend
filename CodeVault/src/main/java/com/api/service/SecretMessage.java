package com.api.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class SecretMessage {

	public String generateMessage() {
		
		String str = "!@#$%^&*_-+=<>?" ;
		
		Random r = new Random();
		
		String secret = "";
		
		for(int i=1; i<=12; i++) {
			int num = r.nextInt(str.length());
			secret = secret + str.charAt(num);
		}
		
		return secret;
		
	}
}
