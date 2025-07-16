package com.example.projetobrecho20.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.useCase.CreateProductUseCase;

public class CreateProductViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public CreateProductViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CreateProductViewModel.class)) {
            CreateProductUseCase useCase = new CreateProductUseCase(context);
            return (T) new CreateProductViewModel(useCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
