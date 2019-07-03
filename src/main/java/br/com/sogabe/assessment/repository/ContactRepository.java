package br.com.sogabe.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sogabe.assessment.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
	Optional<Contact> findByIdAndPersonId(Long id, Long personId);
}
