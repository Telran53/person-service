package telran.java53.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java53.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
