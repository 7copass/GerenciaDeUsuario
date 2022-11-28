package com.manager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Idade não pode ser null")
	@Min(value = 18, message = "Idade inválida")
	private int age;
	
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String uf;
	private String ibge;
	
	@OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)//para poder excluir a classe com que se relaciona
	private List<Contact> contacts;
	
}
