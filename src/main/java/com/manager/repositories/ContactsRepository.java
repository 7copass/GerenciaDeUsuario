package com.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.Contact;

@Repository
@Transactional
public interface ContactsRepository extends JpaRepository<Contact,Long> {
	
	
	@Query("select c from Contact c where c.person.id = ?1")
	public List<Contact> getContacts(Long personId );
}
