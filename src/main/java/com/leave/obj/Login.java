package com.leave.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")//, uniqueConstraints = @UniqueConstraint(columnNames = { "username", "password" }))
// @NamedQuery(query="from Login e", name="getEmployee")
//@Proxy(lazy = false)
public class Login implements Serializable {
//	public Login() {
//		super();
//	}

//	public Login(String firstName, String lastName, String username, String password) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.username = username;
//		this.password = password;
//	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	public String getFirstName() {
		return firstName;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
