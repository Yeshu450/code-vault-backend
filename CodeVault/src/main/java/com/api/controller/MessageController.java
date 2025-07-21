package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Message;
import com.api.repo.MessageRepo;
import com.api.service.SecretMessage;

@RestController
@CrossOrigin(origins="http://localhost:5173/")
public class MessageController {

	@Autowired
	private MessageRepo mr;
	
	@Autowired
	private SecretMessage sm;
	
	
	@PostMapping("/encryptMsg")
	public String encryptMsg(String msg, String pwd) {
		
		 Message m = new Message();
		 String secret = sm.generateMessage();
		 
		 m.setMsg(msg);
		 m.setPwd(pwd);
		 m.setSecretmsg(secret);
		 
		 mr.save(m);
		 
		return secret;
	 
	}
	
	@PostMapping("/decryptMsg")
	public String decryptMsg(String secret, String pwd) {
		
		System.out.println(secret + " " + pwd);
		
		List<Message> list = mr.findBySecretmsgAndPwd(secret,pwd);
		
		if(list.size()==1) {
			return list.get(0).getMsg() ;
		}
		
		else {
			return "No Message available" ;
		}
		
	}
	
}
