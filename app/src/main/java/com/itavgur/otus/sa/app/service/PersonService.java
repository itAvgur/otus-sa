package com.itavgur.otus.sa.app.service;

import com.itavgur.otus.sa.app.domain.Person;
import com.itavgur.otus.sa.app.exception.NotFoundException;
import com.itavgur.otus.sa.app.repository.PersonRepository;
import com.itavgur.otus.sa.app.web.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


    public Iterable<Person> getPerson() {

        return personRepository.findAll();
    }


    public Person getPersonEnity(Long id) throws NotFoundException {
        return personRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public PersonDto getPerson(Long id) throws NotFoundException {
        Person person = getPersonEnity(id);
        return getPersonDto(person, id);
    }


    public PersonDto createPerson(PersonDto personDto) {
        Person personNew = personRepository.save(getPerson(personDto, null));
        return getPersonDto(personNew, personNew.getId());
    }

    public PersonDto updatePerson(PersonDto personDto, Long id) throws NotFoundException {
        getPerson(id);
        Person save = personRepository.save(getPerson(personDto, id));
        return getPersonDto(save, id);
    }


    public void deletePerson(Long id) throws NotFoundException {
        Person person = getPersonEnity(id);
        personRepository.delete(person);
    }

    private PersonDto getPersonDto(Person person, Long id) {
        return new PersonDto(
                id,
                person.getFirstName(),
                person.getLastName(),
                person.getCity(),
                person.getEnabled()
        );

    }

    private Person getPerson(PersonDto personDto, Long id) {
        return new Person(
                id,
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getCity(),
                personDto.getEnabled()
        );

    }

}
