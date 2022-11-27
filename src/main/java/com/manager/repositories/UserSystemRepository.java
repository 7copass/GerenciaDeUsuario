package com.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.UserSystem;

@Repository
@Transactional
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {
	
	UserSystem findByLogin(String login);
}
