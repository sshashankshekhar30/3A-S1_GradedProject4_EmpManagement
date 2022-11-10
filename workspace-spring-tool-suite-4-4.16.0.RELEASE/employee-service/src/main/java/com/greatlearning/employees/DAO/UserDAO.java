package com.greatlearning.employees.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employees.Model.Users;

public interface UserDAO extends JpaRepository<Users, Long> {
	
	public Users getUserByUsername(String username);
	
	public List<Users> findAll();

	public Optional<Users> findById(long theId);

	public Users save(Users theUser);

	public void deleteById(long theId);

	public Optional<Users> findByUserName(String userName);


}
