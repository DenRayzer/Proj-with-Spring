package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<Person> getAllPersons() {
//        return (List<Person>) personRepository.findAll();
        List<Person> result = new ArrayList<>();
        personRepository.findAll().forEach(result::add);
        return result;
    }


    public List<Person> search(String name) {
        return personRepository.findByNameLike("%" + name + "%");
        /*return personRepository.findAll().stream()
                .filter(c -> c.getName().contains(name))
                .collect(Collectors.toList());*/
    }


    public Optional<Person> getPersonById(int id) {
        return personRepository.findById(id);
    }


    public void updatePerson(Person person) {
//        personRepository.update(person);
        personRepository.deleteById(person.getId());
    }


    public void addPerson(Person person) {
        personRepository.save(person);
    }


    public boolean deletePerson(int id) {
//        return personRepository.deleteById(id);
        return true;
    }
} 