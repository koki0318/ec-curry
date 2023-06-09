package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * お届け先自動補完を行うクラス.
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class InputAssistSevice {

	@Autowired
	private UserRepository userRepository;
	
	
	public User load(Integer userId) {
		return userRepository.load(userId);

	}
}
