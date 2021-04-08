package com.itavgur.otus.sa.app.service;

import com.itavgur.otus.sa.app.domain.Person;
import com.itavgur.otus.sa.app.exception.NotFoundException;
import com.itavgur.otus.sa.app.repository.PersonRepository;
import com.itavgur.otus.sa.app.web.dto.PersonDto;
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
                person.getLogin(),
                person.getEmail(),
                person.getFirstName(),
                person.getLastName(),
                person.getCity()
        );
    }

    private Person getPerson(PersonDto personDto, Long id) {
        return new Person(
                id,
                personDto.getLogin(),
                personDto.getEmail(),
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getCity()
        );
    }

}
