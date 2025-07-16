package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.repository.BagRepository;

public class SetBagAsSoldUseCase {
    private final BagRepository bagRepository;

    public SetBagAsSoldUseCase(Context context) {
        this.bagRepository = new BagRepository(context);
    }

    public void execute(String bagId) {
        bagRepository.setBagAsSold(bagId);
    }
}
