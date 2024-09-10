package telran.java53.person.service;

import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.ChildDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.EmployeeDto;
import telran.java53.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	PersonDto removePerson(Integer id);
	
	PersonDto updatePersonName(Integer id, String name);
	
	PersonDto updatePersonAddress(Integer id, AddressDto addressDto);
	
	PersonDto[] findPersonsByCity(String city);
	
	PersonDto[] findPersonsByName(String name);
	
	PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge);
	
	Iterable<CityPopulationDto> getCitiesPopulation();
	
    Iterable<EmployeeDto> findEmployeeBySalary(int min, int max);

	Iterable<ChildDto> getChildren();

}
