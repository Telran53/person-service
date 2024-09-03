package telran.java53.person.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java53.person.dao.PersonRepository;
import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.dto.exception.PersonNotFoundException;
import telran.java53.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if(personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto[] findPersonsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto[] findPersonsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

}
