package br.com.sogabe.assessment.api;

import java.util.Set;

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

import br.com.sogabe.assessment.model.Contact;
import br.com.sogabe.assessment.model.Person;
import br.com.sogabe.assessment.repository.ContactRepository;
import br.com.sogabe.assessment.repository.PersonRepository;

@RestController
@RequestMapping("/api/people")
public class ContactApiController {

	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@GetMapping("/{personId}/contacts")
	public Set<Contact> getAllContactsByPersonId(@PathVariable(value = "personId") Long personId) {
		Person person = personRepo.findById(personId)
				.orElseThrow(() -> new IllegalArgumentException("Person not found: "+personId));
		
		return person.getContacts();
	}
	
	@PostMapping("/{personId}/contacts")
	public Contact createContact(@PathVariable(value = "personId") Long personId, @Valid @RequestBody Contact contact) {
		Person person = personRepo.findById(personId)
				.orElseThrow(() -> new IllegalArgumentException("Person not found: "+personId));
		
		contact.setPerson(person);
		
		return contactRepo.save(contact);
	}
	
	@SuppressWarnings("unused")
	@PutMapping("/{personId}/contacts/{contactId}")
	public ResponseEntity<Contact> updateContact(@PathVariable(value = "personId") Long personId, @PathVariable(value = "contactId") Long contactId, @Valid @RequestBody Contact contactData) throws IllegalArgumentException {
		Person person = personRepo.findById(personId)
				.orElseThrow(() -> new IllegalArgumentException("Person not found: "+personId));
		
		Contact contact = contactRepo.findById(contactId)
			.orElseThrow(() -> new IllegalArgumentException("Contact not found: "+contactId));
		
		contact.setType(contactData.getType());
		contact.setValue(contactData.getValue());
	    contact = contactRepo.save(contact);
	    
	    return ResponseEntity.ok(contact);
	}

	@DeleteMapping("/{personId}/contacts/{contactId}")
	public ResponseEntity<?> deleteContact(@PathVariable(value = "personId") Long personId, @PathVariable(value = "contactId") Long contactId) throws IllegalArgumentException {
		Contact contact = contactRepo.findByIdAndPersonId(contactId, personId)
				.orElseThrow(() -> new IllegalArgumentException("Person or contact not found: "+personId+" or "+contactId));
		
		contactRepo.delete(contact);
		
		return ResponseEntity.ok().build();
	}
}
