package com.app.serviceImple;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.entity.UserEntity;
import com.app.repoInterface.RepoInterface;
import com.app.response.UserResponse;
import com.app.serviceInterface.ServiceInterface;

@Service
@Transactional
public class ServiceImple implements ServiceInterface {

	@Autowired
	RepoInterface repo;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Override
	public UserResponse createUser(UserDao dao) {

		UserEntity check = repo.findByUserId(dao.getUserId());
		UserEntity saveUser = new UserEntity();
		UserEntity copy = new UserEntity();

		UserResponse result = new UserResponse();

		if (check == null) {

			BeanUtils.copyProperties(dao, saveUser);

			saveUser.setPassword(bCrypt.encode(dao.getPassword()));
			copy = repo.save(saveUser);
			BeanUtils.copyProperties(copy, result);

		}

		return result;
	}

	@Override
	public UserResponse getUserDetails(UserDao dao) {

		UserEntity check = repo.findByUserId(dao.getUserId());
		UserResponse result = new UserResponse();

		if (check != null) {
			BeanUtils.copyProperties(check, result);

		}

		return result;
	}

}