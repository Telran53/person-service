package telran.java53.person.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java53.person.dao.PersonRepository;
import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.ChildDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.EmployeeDto;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.dto.exception.PersonNotFoundException;
import telran.java53.person.model.Address;
import telran.java53.person.model.Child;
import telran.java53.person.model.Employee;
import telran.java53.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		if (personDto instanceof ChildDto) {
			personRepository.save(modelMapper.map(personDto, Child.class));
			return true;
		}
		if (personDto instanceof EmployeeDto) {
			personRepository.save(modelMapper.map(personDto, Employee.class));
			return true;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		if (person instanceof Child) {
			return modelMapper.map(person, ChildDto.class);
		}
		if (person instanceof Employee) {
			return modelMapper.map(person, EmployeeDto.class);
		}
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setName(name);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setAddress(modelMapper.map(addressDto, Address.class));
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto[] findPersonsByCity(String city) {
		return personRepository.findByAddressCityIgnoreCase(city).map(p -> modelMapper.map(p, PersonDto.class))
				.toArray(PersonDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto[] findPersonsByName(String name) {
		return personRepository.findByNameIgnoreCase(name).map(p -> modelMapper.map(p, PersonDto.class))
				.toArray(PersonDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge) {
		LocalDate from = LocalDate.now().minusYears(maxAge);
		LocalDate to = LocalDate.now().minusYears(minAge);
		return personRepository.findByBirthDateBetween(from, to).map(p -> modelMapper.map(p, PersonDto.class))
				.toArray(PersonDto[]::new);
	}

	@Override
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		return personRepository.getCityPopulation();
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
					new Address("Tel Aviv", "Ben Gvirol", 81));
			Child child = new Child(2000, "Mosche", LocalDate.of(2019, 7, 5), new Address("Ashkelon", "Bar Kohva", 21),
					"Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23),
					new Address("Rehovot", "Herzl", 7), "Motorola", 20_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}

	}

}
