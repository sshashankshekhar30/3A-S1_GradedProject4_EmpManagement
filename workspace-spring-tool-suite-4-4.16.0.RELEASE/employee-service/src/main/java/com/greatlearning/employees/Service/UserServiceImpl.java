package com.greatlearning.employees.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.greatlearning.employees.DAO.UserDAO;
import com.greatlearning.employees.Model.Users;

@Service
public class UserServiceImpl implements UserDAO {

	@Autowired
	UserDAO userRepository;

	@Override
	@Transactional
	public List<Users> findAll() {
		List<UserS> users = userRepository.findAll();
		return users;
	}

	@Override
	@Transactional
	public Optional<Users> findById(long theId) {
		return userRepository.findById(theId);
	}

	@Override
	@Transactional
	public void save(Users theUser) {
		userRepository.save(theUser);
	}

	@Override
	@Transactional
	public void deleteById(long theId) {
		userRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public Optional<Users> findByUserName(String userName) {
		return Optional.ofNullable(userRepository.getUserByUsername(userName));
	}



}
