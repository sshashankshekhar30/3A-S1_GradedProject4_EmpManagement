package com.greatlearning.employees.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employees.DAO.EmployeeDAO;
import com.greatlearning.employees.Model.Employee;
import com.greatlearning.employees.Model.Roles;
import com.greatlearning.employees.Model.Users;
import com.greatlearning.employees.Service.EmployeeService;

@RestController
@RequestMapping("/restapi")
public class EmpController {
	
private EmployeeDAO edao;
	
	@Autowired
	public EmpController(EmployeeDAO edao)
	{
		this.edao=edao;
	}
	@PostMapping("/saveUser")
    public Users saveUser(@RequestBody Users user) {
        return edao.saveUser(user);
    }

    @PostMapping("/saveRole")
    public Roles saveRole(@RequestBody Roles role) {
        return edao.saveRole(role);
    }


	@GetMapping("/employee/employeesList")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return edao.findAll();
	}

	@GetMapping("/employee/{employee_id}")
	public Optional<Employee> getEmployee(@PathVariable int employee_id) {
		return edao.findById(employee_id);
	}

	@PostMapping("/employee/add")
	public Employee addEmployee(@RequestBody Employee theEmployees) {
		edao.save(theEmployees);
		return theEmployees;

	}

	@PutMapping("/employee/update")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		edao.save(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employee/delete/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		return edao.deleteById(employeeId);

	}


	@GetMapping("/employee/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return edao.searchByFirstName(firstName);
	}

	@GetMapping("/employee/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String sortBy) {
		return edao.sortByFirstName(sortBy);
	}


}
