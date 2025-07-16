package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.repository.BagRepository;

public class CreateBagUseCase {
    private final BagRepository repository;

    public CreateBagUseCase(Context context) {
        this.repository = new BagRepository(context);
    }

    public long execute(Customer customer ) {
        return repository.create(customer);
    }
}
