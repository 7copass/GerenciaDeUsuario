package com.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manager.model.Contact;
import com.manager.model.Person;
import com.manager.repositories.ContactsRepository;
import com.manager.repositories.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	ContactsRepository contactsRepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register-person")
	public ModelAndView start() {
		ModelAndView modelAndView = new ModelAndView("register/register-person");
		modelAndView.addObject("personObj", new Person()); //instanciando um objeto vazio para reaproveitar o form de cadastro
		Iterable<Person> personsIterable = repository.findAll(); //uma lista iteravel do obj que queremos é buscada no BD
		modelAndView.addObject("persons", personsIterable); // aqui setamos que a lista da viu sera adicionada no objetos do BD
		return modelAndView;//retornando para pagina
	}
	@RequestMapping(method = RequestMethod.POST, value = "**/save-person")//ignora tudo que vem antes da barra,metodo salva um registro e renderiza a lista com todos os registros
	public ModelAndView save(@Valid Person person) {
		person.setContacts(contactsRepository.getContacts(person.getId()));// carregando telefones para corrigir erro de updateconf
		
		repository.save(person);		
		ModelAndView modelAndView = new ModelAndView("/register/register-person"); //instancia um obj desse tipo e como atributo a url para onde vai retornar
		Iterable<Person> personsIterable = repository.findAll(); //uma lista iteravel do obj que queremos é buscada no BD
		modelAndView.addObject("persons", personsIterable); // aqui setamos que a lista da view sera adicionada no objetos do BD
		modelAndView.addObject("personObj", new Person());////instanciando um objeto vazio para reaproveitar o form de cadastro
		return modelAndView; // retornamos a view onde será mostrado a lista
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list-persons")
	public ModelAndView persons() {
		ModelAndView modelAndView = new ModelAndView("/register/register-person"); //instancia um obj desse tipo e como atributo a url para onde vai retornar
		Iterable<Person> personsIterable = repository.findAll(); //uma lista iteravel do obj que queremos é buscada no BD
		modelAndView.addObject("persons", personsIterable); // aqui setamos que a lista da view sera adicionada no objetos do BD
		modelAndView.addObject("personObj", new Person());////instanciando um objeto vazio para reaproveitar o form de cadastro
		return modelAndView; // retornamos a view onde será mostrado a lista
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/update-person/{idPerson}")
	public ModelAndView update(@PathVariable("idPerson") Long idPerson) {
		ModelAndView modelAndView = new ModelAndView("/register/register-person");
		Optional< Person> person = repository.findById(idPerson);
		modelAndView.addObject("personObj", person.get());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "**/delete-person/{idPerson}")
	public ModelAndView delete(@PathVariable("idPerson") Long idPerson) {
		ModelAndView modelAndView = new ModelAndView("/register/register-person");
		repository.deleteById(idPerson);
		modelAndView.addObject("personObj", new Person());////instanciando um objeto vazio para reaproveitar o form de cadastro
		modelAndView.addObject("persons", repository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/searchPerson")
	public ModelAndView Search(@RequestParam("searchName") String searchName,
			@RequestParam ("searchGender") String searchGender) {
		
		List<Person> persons = new ArrayList<Person>();
		if(searchGender != null && !searchGender.isEmpty()) {
			persons = repository.findByGender(searchGender);
		}else {
			persons = repository.findByNameContainingIgnoreCase(searchName);
		}
		ModelAndView modelAndView = new ModelAndView("/register/register-person");
		modelAndView.addObject("persons", persons);
		modelAndView.addObject("personObj", new Person());////instanciando um objeto vazio para reaproveitar o form de cadastro
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contacts/{idPerson}")
	public ModelAndView details(@PathVariable("idPerson") Long idPerson) {
		ModelAndView modelAndView = new ModelAndView("/register/contacts");
		Optional< Person> person = repository.findById(idPerson);
		modelAndView.addObject("personObj", person.get());
		modelAndView.addObject("contacts",contactsRepository.getContacts(idPerson));
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/add-phone/{idPerson}")
	public ModelAndView addPhone(Contact telephone, @PathVariable(name = "idPerson") Long idPerson){
		
		Person person = repository.findById(idPerson).get();
		
		ModelAndView modelAndView = new ModelAndView("register/contacts");
		telephone.setPerson(person);
		contactsRepository.save(telephone);	
		modelAndView.addObject("personObj", person);
		modelAndView.addObject("contacts",contactsRepository.getContacts(idPerson));
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "**/removePhone/{Phoneid}")
	public ModelAndView removePhone(@PathVariable("Phoneid") Long Phoneid) {
		
		Person person = contactsRepository.findById(Phoneid).get().getPerson();
		
		
		
		ModelAndView modelAndView = new ModelAndView("register/contacts");
		contactsRepository.deleteById(Phoneid);
		modelAndView.addObject("personObj", person);
		modelAndView.addObject("contacts",contactsRepository.getContacts(person.getId()));
		
		return modelAndView;
	}
	
	
	
	
	
	
}
