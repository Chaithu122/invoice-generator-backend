package com.Web.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Web.Entity.User;
import com.Web.Repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public User saveOrUpdateUser(User user) {
		Optional<User> optionalUser=userRepository.findByClerkId(user.getClerkId());
		if(optionalUser.isPresent()) {
			User existingUser =optionalUser.get();
			existingUser.setEmail(user.getEmail());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setPhotoUrl(user.getPhotoUrl());
			existingUser =userRepository.save(existingUser);
			return existingUser;
		}
		return userRepository.save(user);
	}


	public void deleteAccount(String clerkId) {
		User existingUser =userRepository.findByClerkId(clerkId).orElseThrow(()->new RuntimeException("User not found"));
		userRepository.delete(existingUser);

	}
	public User getAccountByClerkId(String clerkId) {
		return userRepository.findByClerkId(clerkId).orElseThrow(()->new RuntimeException("User not found"));

	}


}
