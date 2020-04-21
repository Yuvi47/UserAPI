package com.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.controllerInterface.ControllerInterface;
import com.app.dao.UserDao;
import com.app.response.UserResponse;
import com.app.serviceInterface.ServiceInterface;
import com.app.user.details.UserDetails;

@RestController
@RequestMapping(value = "/users")
public class UserController implements ControllerInterface {

	@Autowired
	ServiceInterface service;

	@GetMapping(value = "/status/check")
	public ResponseEntity<UserResponse> getUserDeatils(@RequestBody UserDetails user) {

		UserResponse result = new UserResponse();
		UserDao dao = new UserDao();

		if (user != null) {
			BeanUtils.copyProperties(user, dao);

			result = service.getUserDetails(dao);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserDetails user) {

		UserResponse result = new UserResponse();

		UserDao dao = new UserDao();

		if (user != null) {
			BeanUtils.copyProperties(user, dao);

			result = service.createUser(dao);

		}

		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}
