package com.manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

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
