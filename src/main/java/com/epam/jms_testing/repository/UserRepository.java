package com.epam.jms_testing.repository;



import com.epam.jms_testing.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUserName(String userName);
	void deleteById(Long id);
}
