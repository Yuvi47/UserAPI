package com.app.controllerInterface;

import org.springframework.http.ResponseEntity;

import com.app.response.UserResponse;
import com.app.user.details.UserDetails;

public interface ControllerInterface {

	ResponseEntity<UserResponse>  getUserDeatils(UserDetails user);

	ResponseEntity<UserResponse>  createUser(UserDetails user);
}
