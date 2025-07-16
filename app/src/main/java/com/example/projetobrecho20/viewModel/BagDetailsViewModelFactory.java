package com.example.projetobrecho20.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.useCase.GetBagDetailsUseCase;
import com.example.projetobrecho20.useCase.SetBagAsSoldUseCase;

public class BagDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public BagDetailsViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BagDetailsViewModel.class)) {
            GetBagDetailsUseCase getBagDetailsUseCase = new GetBagDetailsUseCase(context);
            SetBagAsSoldUseCase setBagAsSoldUseCase = new SetBagAsSoldUseCase(context);
            return (T) new BagDetailsViewModel(getBagDetailsUseCase, setBagAsSoldUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
