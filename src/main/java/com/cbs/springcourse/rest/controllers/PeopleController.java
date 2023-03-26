package com.cbs.springcourse.rest.controllers;

import com.cbs.springcourse.rest.dto.PersonDTO;
import com.cbs.springcourse.rest.models.Person;
import com.cbs.springcourse.rest.repositories.PeopleRepository;
import com.cbs.springcourse.rest.services.PeopleService;
import com.cbs.springcourse.rest.util.PersonErrorResponse;
import com.cbs.springcourse.rest.util.PersonNotCreatedException;
import com.cbs.springcourse.rest.util.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller + @ResponseBody над кожним методом
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService,
                            ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList()); // Jackson конвертує ці об'єкти у JSON
    }

    private PersonDTO convertToPersonDTO(Person person){
       return modelMapper.map(person, PersonDTO.class);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id)); // Jackson конвертує у JSON
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, /*PersonDTO personDTO*/
                                             BindingResult bindingResult) {
        System.out.println("find errors");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());

        }
        //peopleService.save(person);
        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
