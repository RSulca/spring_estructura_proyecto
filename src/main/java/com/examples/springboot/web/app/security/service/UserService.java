package com.examples.springboot.web.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.springboot.web.app.security.entity.User;
import com.examples.springboot.web.app.security.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Optional<User> getByUserName(String userName){
		return userRepository.findByUserName(userName);
	}
	
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
}
