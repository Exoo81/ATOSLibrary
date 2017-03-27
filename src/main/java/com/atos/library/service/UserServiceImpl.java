package com.atos.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.library.entity.User;
import com.atos.library.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addUserToLibrary(User user) {
		userRepository.save(user);

	}

}
