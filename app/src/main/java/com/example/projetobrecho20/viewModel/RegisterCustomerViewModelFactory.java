package com.example.projetobrecho20.viewModel;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.useCase.RegisterCustomerUseCase;

public class RegisterCustomerViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public RegisterCustomerViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterCustomerViewModel.class)) {
            RegisterCustomerUseCase useCase = new RegisterCustomerUseCase(context);
            return (T) new RegisterCustomerViewModel(useCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }}
