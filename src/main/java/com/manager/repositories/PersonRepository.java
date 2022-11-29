package com.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.Person;
@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByNameContainingIgnoreCase(String searchName);
	List<Person> findByGender(String searchGender);
	

}
