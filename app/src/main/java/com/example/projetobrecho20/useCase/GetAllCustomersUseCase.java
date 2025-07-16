package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.repository.CustomerRepository;

import java.util.ArrayList;

public class GetAllCustomersUseCase {
    private final CustomerRepository repository;

    public GetAllCustomersUseCase(Context context) {
        this.repository = new CustomerRepository(context);
    }

    public ArrayList<Customer> execute() {
        return  repository.getAllCustomers();
    }
}
