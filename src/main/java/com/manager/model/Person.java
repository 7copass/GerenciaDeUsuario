package com.manager.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Nome não pode ser vazio")
	@NotNull(message = "Nome não pode ser null")
	private String name;
	
	@NotEmpty(message = "SobreNome não pode ser vazio")
	@NotNull(message = "SobreNome não pode ser null")
	private String lastName;
	@NotEmpty(message = "Idade não pode ser vazio")
	@NotNull(message = "Idade não pode ser null")
	@Min(value = 18, message = "Idade inválida")
	private int age;
	
	@OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)//para poder excluir a classe com que se relaciona
	private List<Contact> contacts;
	
}
