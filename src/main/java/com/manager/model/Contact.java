package com.manager.model;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	private String phone;
	
	private String type;
	
	@SuppressWarnings("removal")
	@ManyToOne
	@ForeignKey(name = "person_id")
	private Person person;
	
}
