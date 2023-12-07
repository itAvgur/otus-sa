package com.itavgur.otus.sa.person.service;

import com.itavgur.otus.sa.person.domain.Person;
import com.itavgur.otus.sa.person.exception.NotFoundException;
import com.itavgur.otus.sa.person.repository.PersonRepository;
import com.itavgur.otus.sa.person.web.dto.PersonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PersonService {

    PersonRepository personRepository;

    public Iterable<Person> getPersonAll() {
        return personRepository.findAll();
    }

    public Person getPersonEntity(Long id) throws NotFoundException {
        return personRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Person getPersonEntityByLogin(String userLogin) throws NotFoundException {
        return personRepository.findByLogin(userLogin).orElseThrow(NotFoundException::new);
    }

    public PersonDto getPersonFromDto(String userLogin) throws NotFoundException {
        Person person = getPersonEntityByLogin(userLogin);
        return getPersonDto(person);
    }

    public PersonDto getPersonDtoById(Long id, String userLogin) throws NotFoundException {
        Person person = getPersonEntity(id);
        return person.getLogin().equals(userLogin) ? getPersonDto(person) : null;
    }

    public PersonDto createPerson(PersonDto personDto) {
        Person personNew = personRepository.save(getPersonFromDto(personDto, null));
        return getPersonDto(personNew);
    }

    public PersonDto updatePerson(PersonDto personNew, String userLogin) throws NotFoundException {
        Person personDb = getPersonEntityByLogin(userLogin);
        Person personToSave = getPersonFromDto(personNew, null);
        personToSave.setId(personDb.getId());
        personToSave.setLogin(personDb.getLogin());
        Person save = personRepository.save(personToSave);
        return getPersonDto(save);
    }

    public void deletePerson(Long id) throws NotFoundException {
        Person person = getPersonEntity(id);
        personRepository.delete(person);
    }

    private PersonDto getPersonDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getLogin(),
                person.getEmail(),
                person.getFirstName(),
                person.getLastName(),
                person.getCity(),
                person.getBirthDate()
        );
    }

    private Person getPersonFromDto(PersonDto personDto, Long id) {
        return new Person(
                id,
                personDto.getLogin(),
                personDto.getEmail(),
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getCity(),
                personDto.getBirthDate()
        );
    }

}
