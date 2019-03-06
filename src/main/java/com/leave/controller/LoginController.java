package com.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leave.dtos.LoginDTO;
import com.leave.dtos.RegisterDTO;
import com.leave.obj.Login;
import com.leave.services.LoginService;
import com.leave.services.RegisterService;;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	RegisterService registerService;
  
	@CrossOrigin(origins = "http://localhost:4200")

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public LoginDTO getEmployee(@RequestBody LoginDTO loginDTO) {

		LoginDTO user = loginService.loginEmployee(loginDTO);
		return user;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public Login getEmployee(@RequestBody RegisterDTO registerDTO) {

		return registerService.registerEmployee(registerDTO.getEmpId(), registerDTO.getFirstName(),
				registerDTO.getLastName(), registerDTO.getEmail(), registerDTO.getUsername(),
				registerDTO.getPassword());


	}
}
