package com.intellect.user.service;

import org.springframework.stereotype.Service;

import com.intellect.user.dto.UserDTO;
import com.intellect.user.dto.UserEntityResponse;
import com.intellect.user.dto.UserResponse;
@Service
public interface UserService {
	UserResponse saveUser(UserDTO request);
	String deleteUser(Long userId);
	UserEntityResponse findUser(Long id);

}
