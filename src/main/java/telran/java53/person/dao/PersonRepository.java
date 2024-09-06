package telran.java53.person.dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java53.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Set<Person> findByNameIgnoreCase(String name);
	
	Set<Person> findByAddressCityIgnoreCase(String city);
	
	Set<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

}
