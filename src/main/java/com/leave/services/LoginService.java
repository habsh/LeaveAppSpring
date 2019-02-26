package com.leave.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leave.repositories.LoginRepository;

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
	public Optional<Login> loginEmployee(String username, String password) {
		
		Optional<Login> login = Optional.ofNullable(loginRepository.findByUsername(username));
		
		if(login.isPresent())
			return password.equals(login.get().getPassword())?login:null;
		else 
			return null;
	}

}
