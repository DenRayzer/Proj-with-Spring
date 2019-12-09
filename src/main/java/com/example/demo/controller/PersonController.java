package com.example.demo.controller;

import com.example.demo.entity.Payment;
import com.example.demo.entity.Person;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/persons")
    private List<Person> getAllPersons() {
        return personService.getAllPersons();
    }


    @GetMapping("/persons/{id}")
    private ResponseEntity<Person> getPerson(@PathVariable("id") int personId) {
        Optional<Person> result = personService.getPersonById(personId);
        return ResponseEntity
                .status(result.get() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(result.get());
    }


    @GetMapping("/persons/search/{name}")
    public List<Person> getEmployeeByName(@PathVariable("name") String name) {
        return personService.search(name);
    }


    @DeleteMapping("/persons/{id}")
    private ResponseEntity<String> deletePerson(@PathVariable("id") int personId) {
        boolean flag = personService.deletePerson(personId);

        if (flag)
            return ResponseEntity.status(HttpStatus.OK)
                    .body("person deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("person not found");
    }


    @PostMapping("/persons")
    private ResponseEntity<String> savePerson(@RequestBody Person person) {
        if (person == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        personService.addPerson(person);
        return new ResponseEntity<>("person added", HttpStatus.OK);
    }


    @PutMapping("/persons")
    private ResponseEntity<String> updatePerson(@RequestBody Person person) {
        if (person == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        personService.updatePerson(person);
        return new ResponseEntity<>("person updated", HttpStatus.OK);
    }

    @GetMapping("/payments")
    private List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/persons/{id}/payments")
    private List<Payment> getPaymentByPerson(@PathVariable("id") int personId) {
        return paymentService.getPaymentByPerson(personId);
    }
}