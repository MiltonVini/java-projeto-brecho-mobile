package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.repository.ProductRepository;

import java.util.ArrayList;

public class GetAllProductsUseCase {
    private final ProductRepository repository;

    public GetAllProductsUseCase(Context context) {
        this.repository = new ProductRepository(context);
    }

    public ArrayList<Product> execute() {
        return repository.getAllProducts();
    }
}
