package br.com.sogabe.assessment.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sogabe.assessment.model.Person;
import br.com.sogabe.assessment.repository.PersonRepository;

@RestController
@RequestMapping("/api/people")
public class PersonApiController {

	@Autowired
	private PersonRepository personRepo;
	
	@GetMapping("/")
	public List<Person> getAllPeople() {
		return personRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long id) throws IllegalArgumentException {
		System.out.println(id);
		Person person = personRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Person not found: "+id));
		
		return ResponseEntity.ok().body(person);
	}
	
	@PostMapping("/")
	public Person createPerson(@Valid @RequestBody Person person) {
		return personRepo.save(person);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long id, @Valid @RequestBody Person personData) throws IllegalArgumentException {
		Person person = personRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Person not found: "+id));
		
	    person.setName(personData.getName());
	    person = personRepo.save(person);
	    
	    return ResponseEntity.ok(person);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws IllegalArgumentException {
		Person person = personRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Person not found: "+id));
		
		personRepo.delete(person);
		
		return ResponseEntity.ok().build();
	}
}
