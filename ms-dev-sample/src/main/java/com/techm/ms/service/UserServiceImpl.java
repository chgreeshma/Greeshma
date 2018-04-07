package com.techm.ms.service;

import java.util.List;

import java.util.concurrent.atomic.AtomicLong;


import com.techm.ms.model.User;

public class UserServiceImpl implements UserService{
	

	
private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;
	public List<User> findAllUsers() {
		return users;
	}

	public User getUserById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	} 
	public void createUser(User user) {		
		counter.incrementAndGet();
		users.add(user);
	}
	
	
}
