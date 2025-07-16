package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.repository.ProductRepository;

public class CreateProductUseCase {
    private final ProductRepository repository;
    public CreateProductUseCase(Context context) {
        this.repository = new ProductRepository(context);
    }

    public long execute(Product product) {

        return repository.insert(product);
    }



}

