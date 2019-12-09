package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        List<Payment> result = new ArrayList<>();
        paymentRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Payment> getPaymentByPerson(int pid) {
        List<Payment> result = new ArrayList<>();
        for (Payment p : paymentRepository.findAll()) {
            if (p.getPerson().getId() == pid) {
                result.add(p);
            }
        }
        return result;
    }
}
