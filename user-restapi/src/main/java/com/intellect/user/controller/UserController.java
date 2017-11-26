package com.intellect.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.user.dto.UserDTO;
import com.intellect.user.dto.UserEntityResponse;
import com.intellect.user.dto.UserResponse;
import com.intellect.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*
 * User Controller
 */
@RestController
@Api(tags = "User", description = "API for User")
public class UserController {
	@Autowired
	private UserService service;

	/*
	 * Save Or Update User , if Id is null or 0 it will insert the User or it
	 * will update existing entity.
	 * 
	 */
	@RequestMapping(value = { "/api/user/save" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.PUT })
	@ApiOperation(value = "Save Or Update", notes = "Save Or Update The User", response = UserResponse.class)
	@ResponseBody
	public UserResponse saveUser(@RequestBody UserDTO request) {
		return service.saveUser(request);
	}

	@RequestMapping(value = { "/api/user/delete" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ApiOperation(value = "Delete User ", notes = "Delete User Based on Id ", response = String.class)
	@ResponseBody
	public String deletUser(@RequestParam Long userId) {
		return service.deleteUser(userId);
	}
	
	@RequestMapping(value = { "/api/user/findById" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ApiOperation(value = "Find User", notes = "Find User By Id", response = UserEntityResponse.class)
	@ResponseBody
	public UserEntityResponse findUser(@RequestParam Long userId) {
		return service.findUser(userId);
	}
}
