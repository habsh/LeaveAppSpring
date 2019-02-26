package com.leave.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.leave.services.LoginService;
import com.leave.dtos.LoginDTO;
import com.leave.obj.Login;;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	LoginService loginService;

//	@RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json")
//	public List<Login> getEmployees() {
//
//		List<Login> listOfUsers = loginService.getAllEmployees();
//		return listOfUsers;
//	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public Login getEmployee(@RequestBody LoginDTO loginDTO) {
		
		Optional<Login> user = loginService.loginEmployee(loginDTO.getUsername(), loginDTO.getPassword());
		
		if(user != null && user.isPresent())
			return user.get();
		else
			return null;
		
	}

	
}
