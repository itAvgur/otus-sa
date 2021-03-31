package com.itavgur.otus.sa.app.web;

import com.itavgur.otus.sa.app.domain.Person;
import com.itavgur.otus.sa.app.exception.NotFoundException;
import com.itavgur.otus.sa.app.service.PersonService;
import com.itavgur.otus.sa.app.web.dto.PersonDto;
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
