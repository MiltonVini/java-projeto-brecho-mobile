package com.example.projetobrecho20.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.useCase.RegisterCustomerUseCase;

import java.util.UUID;

public class RegisterCustomerViewModel extends ViewModel {
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public RegisterCustomerViewModel(RegisterCustomerUseCase registerCustomerUseCase) {
        this.registerCustomerUseCase = registerCustomerUseCase;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void consumeToast() {
        toastMessage.setValue(null);
    }

    public void onRegisterCustomerButtonClick(String name, String instagramUser, String email) {
        if (name.isEmpty() || instagramUser.isEmpty() || email.isEmpty()) {
            toastMessage.setValue("Preencha todos os campos!");
            return;
        }

        try {
            Customer customer = new Customer(String.valueOf(UUID.randomUUID()), name, instagramUser, email);
            long result = registerCustomerUseCase.execute(customer);

            if (result > 0) {
                toastMessage.setValue("Cliente Registrado com sucesso!");
            }


        } catch (Exception e) {
            toastMessage.setValue("Algo deu errado!");
            e.printStackTrace();
        }

    }
}