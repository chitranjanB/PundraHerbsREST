package com.rest.pundraherbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.entity.User;
import com.rest.pundraherbs.repository.UserRepository;


@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	public User getUserById(Long userId) {
		User user = userRepository.getOne(userId);
		return user;
	}
}
