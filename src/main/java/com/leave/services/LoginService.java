package com.leave.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leave.repositories.LoginRepository;
import com.leave.dtos.LoginDTO;
import com.leave.exceptions.UserNotFoundException;
import com.leave.obj.Login;

@Service("loginService")
public class LoginService {

	@Autowired
	LoginRepository loginRepository;

	@Transactional
	public List<Login> getAllEmployees() {
		return loginRepository.findAll();
	}

	@Transactional
	public LoginDTO loginEmployee(LoginDTO user) {

		Optional<Login> login = Optional.ofNullable(loginRepository.findByUsername(user.getUsername()));

		if (login.isPresent()){
			user.setUser(user.getPassword().equals(login.get().getPassword()));
			user.setEmpId(login.get().getEmpId());
			return user;
		}
		else
			throw new UserNotFoundException("User not found");
	}

}
