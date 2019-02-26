package com.leave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leave.obj.Login;


@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
	Login findByUsername(String username);
//
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public void setSessionFactory(SessionFactory sf) {
//		this.sessionFactory = sf;
//	}
//	public List<Login> getAllUsers() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<Login> userList = session.createQuery("from employee").list();
//		return userList;
//	}
//
//	public Login getUser(String username) {
//		Session session = this.sessionFactory.getCurrentSession();
//		Login login = (Login) session.load(Login.class, new String(username));
//		return login;
//	}

}