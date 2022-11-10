package com.greatlearning.employees.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employees.DAO.EmployeeDAO;
import com.greatlearning.employees.DAO.RoleDAO;
import com.greatlearning.employees.DAO.UserDAO;
import com.greatlearning.employees.Model.Employee;
import com.greatlearning.employees.Model.Roles;
import com.greatlearning.employees.Model.Users;

@Service
public class EmployeeServiceImpl implements  EmployeeDAO{

	@Autowired
	private EmployeeDAO employeeRepository;
	@Autowired
	RoleDAO roleRepository;
	@Autowired
	UserDAO userRepository;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(int theId) {
		boolean ifEmployeeExist = employeeRepository.existsById(theId);
		if (ifEmployeeExist) {
			return employeeRepository.findById(theId);

		} else
			throw new RuntimeException("There is no employee exist with id : " + theId);

	}

	@Override
	public void save(Employee theEmployee) {

		if (theEmployee.getFirst_Name().equals("") || theEmployee.getLast_Name().equals("")
				|| theEmployee.getEmailId().equals("")) {
			throw new RuntimeException("Error!!!All fields are mandatory ");

		} else {
			employeeRepository.saveAndFlush(theEmployee);
			
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		boolean ifEmployeeExist = employeeRepository.existsById(employee.getId());

		if (ifEmployeeExist) {
			employeeRepository.saveAndFlush(employee);
		
		} else {
			

		}

	}

	@Override
	public String deleteById(int theId) {

		boolean ifEmployeeExist = employeeRepository.existsById(theId);

		if (ifEmployeeExist) {
			employeeRepository.deleteById(theId);
			return "Deleted employee id -" + theId;
		} else
			return "There is no employee exist with id : " + theId;
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
		if (employees.size() > 0)
			return employees;
		else
			throw new RuntimeException("No employee data found!!!");
	}

	@Override
	public List<Employee> sortByFirstName(String sortBy) {

		List<Employee> employees = employeeRepository.findAll(Sort.by(Direction.fromString(sortBy), "firstName"));
		if (employees.size() > 0)

			return employees;
		else
			throw new RuntimeException("No employee data found!!!");
	}

	@Override
	public Users saveUser(Users user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Roles saveRole(Roles role) {
		return roleRepository.save(role);
	}

	
	

}
