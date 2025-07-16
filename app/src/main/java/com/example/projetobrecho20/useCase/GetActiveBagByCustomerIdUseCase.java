package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.repository.BagRepository;

public class GetActiveBagByCustomerIdUseCase {
    private final BagRepository repository;

    public GetActiveBagByCustomerIdUseCase(Context context) {
        this.repository = new BagRepository(context);
    }

    public Bag execute(Customer customer) {
        return repository.getActiveBagByCustomerId(customer);
    }
}
