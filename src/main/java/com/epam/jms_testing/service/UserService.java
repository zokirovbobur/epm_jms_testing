package com.epam.jms_testing.service;


import com.epam.jms_testing.dao.UserEntity;
import com.epam.jms_testing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements GeneralService<UserEntity> {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public Optional<UserEntity> findByUserName(String userName){
		return repository.findByUserName(userName);
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserEntity save(UserEntity object) {
		return repository.save(object);
	}

	@Override
	public List<UserEntity> getAll() {
		return repository.findAll();
	}

	public void removeById(Long id){
		repository.deleteById(id);
	}
}
