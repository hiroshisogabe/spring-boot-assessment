package br.com.sogabe.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sogabe.assessment.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
