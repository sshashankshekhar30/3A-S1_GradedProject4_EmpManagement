package com.greatlearning.employees.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employees.Model.Employee;
import com.greatlearning.employees.Model.Roles;
import com.greatlearning.employees.Model.Users;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();
	
	public List<Employee> findAll();

	public Optional<Employee> findById(int theId);

	public void save(Employee theEmployee);

	public void updateEmployee(Employee theEmployee);

	public String deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstName(String sortBy);

	public Users saveUser(Users user);

	public Roles saveRole(Roles role);



}
