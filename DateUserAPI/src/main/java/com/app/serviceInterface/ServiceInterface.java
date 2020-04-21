package com.app.serviceInterface;

import com.app.dao.UserDao;
import com.app.response.UserResponse;

public interface ServiceInterface {

	UserResponse getUserDetails(UserDao dao);

	UserResponse createUser(UserDao dao);

}
