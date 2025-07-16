package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.repository.BagRepository;

import java.util.ArrayList;

public class GetAllBagsUseCase {
    private final BagRepository repository;

    public GetAllBagsUseCase(Context context) {
        this.repository = new BagRepository(context);
    }

    public ArrayList<Bag> execute() {
        return this.repository.getAllBags();
    }
}
