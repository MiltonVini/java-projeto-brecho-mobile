package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.repository.CustomerRepository;

public class GetCustomerInfoUseCase {
    private final CustomerRepository repository;

    public GetCustomerInfoUseCase(Context context) {
        this.repository = new CustomerRepository(context);
    }

    public Customer execute(String customerId) {
        return repository.getCustomerById(customerId);
    }
}
