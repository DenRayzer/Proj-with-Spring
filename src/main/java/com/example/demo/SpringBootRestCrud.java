package com.example.demo;

import com.example.demo.entity.Payment;
import com.example.demo.entity.Person;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestCrud implements ApplicationRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRestCrud.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Person person1 = new Person("ivanov", 11);
        Person person2 = new Person("petrov", 22);

        personRepository.save(person1);
        personRepository.save(person2);

        paymentRepository.save(new Payment("pay1", "coffee cup", person1));
        paymentRepository.save(new Payment("pay2", "sweet cake", person1));
        paymentRepository.save(new Payment("pay3", "sugar milk", person2));
    }
}
