package com.example.projetobrecho20.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.useCase.CreateBagUseCase;
import com.example.projetobrecho20.useCase.CreateProductUseCase;
import com.example.projetobrecho20.useCase.GetActiveBagByCustomerIdUseCase;
import com.example.projetobrecho20.useCase.GetAllCustomersUseCase;
import com.example.projetobrecho20.useCase.GetAllProductsUseCase;
import com.example.projetobrecho20.useCase.InsertProductsInBagUseCase;

public class InsertSalesViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public InsertSalesViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InsertSalesViewModel.class)) {
            InsertProductsInBagUseCase insertProductsInBagUseCase = new InsertProductsInBagUseCase(context);
            GetAllCustomersUseCase getAllCustomersUseCase = new GetAllCustomersUseCase(context);
            GetAllProductsUseCase getAllProductsUseCase = new GetAllProductsUseCase(context);
            CreateBagUseCase createBagUseCase = new CreateBagUseCase(context);
            GetActiveBagByCustomerIdUseCase getActiveBagByCustomerIdUseCase = new GetActiveBagByCustomerIdUseCase(context);
            return (T) new InsertSalesViewModel(insertProductsInBagUseCase, getAllCustomersUseCase, getAllProductsUseCase, createBagUseCase, getActiveBagByCustomerIdUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
