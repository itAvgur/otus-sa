package com.itavgur.otus.sa.lab1.web;

import com.itavgur.otus.sa.lab1.domain.Person;
import com.itavgur.otus.sa.lab1.exception.NotFoundException;
import com.itavgur.otus.sa.lab1.service.PersonService;
import com.itavgur.otus.sa.lab1.web.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personalService;

    @GetMapping
    public Iterable<Person> getAll() throws NotFoundException {

        return personalService.getPerson();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable(name = "id") Long id) throws NotFoundException {

        return personalService.getPerson(id);
    }

    @PostMapping
    public PersonDto createPersonal(@RequestBody PersonDto personDto) {

        return personalService.createPerson(personDto);
    }

    @PutMapping("/{id}")
    public PersonDto updatePersonal(@RequestBody PersonDto personDto, @PathVariable("id") Long id) throws NotFoundException {

        return personalService.updatePerson(personDto, id);
    }

    @DeleteMapping("/{id}")
    public void deletePersonal(@PathVariable(name = "id") Long id) throws NotFoundException {
        personalService.deletePerson(id);
    }

}
