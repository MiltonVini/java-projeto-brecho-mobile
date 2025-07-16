package com.example.projetobrecho20.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.useCase.GetAllBagsUseCase;
import com.example.projetobrecho20.useCase.GetCustomerInfoUseCase;

public class ListBagsViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ListBagsViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListBagsViewModel.class)) {
            GetAllBagsUseCase getAllBagsUseCase = new GetAllBagsUseCase(context);
            GetCustomerInfoUseCase getCustomerInfoUseCase = new GetCustomerInfoUseCase(context);
            return (T) new ListBagsViewModel(getAllBagsUseCase, getCustomerInfoUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
