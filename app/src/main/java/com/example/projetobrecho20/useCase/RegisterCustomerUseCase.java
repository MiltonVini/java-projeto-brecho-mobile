package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.repository.CustomerRepository;

public class RegisterCustomerUseCase {
    private final CustomerRepository repository;

    public  RegisterCustomerUseCase(Context context) {
        this.repository = new CustomerRepository(context);
    }

    public long execute(Customer customer) {
        return repository.insert(customer);
    };
}
