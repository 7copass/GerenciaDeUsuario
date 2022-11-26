package com.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.Contact;

@Repository
@Transactional
public interface ContactsRepository extends JpaRepository<Contact,Long> {
	

}
