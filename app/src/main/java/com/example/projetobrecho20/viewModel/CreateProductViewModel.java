package com.example.projetobrecho20.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetobrecho20.R;
import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.useCase.CreateProductUseCase;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductViewModel extends ViewModel {

    private final CreateProductUseCase createProductUseCase;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();


    public CreateProductViewModel(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void consumeToast() {
        toastMessage.setValue(null);
    }


    public void onCreateProductButtonClick(String productDescription, String productPrice, String productCost, int stockTypeRadioId) {

        if (productDescription.isEmpty() || productPrice.isEmpty() || productCost.isEmpty()) {
            toastMessage.setValue("Preencha todos os campos");
            return;
        }


        Product.StockType stockType;
        if (stockTypeRadioId == R.id.uniqueStockRadioButton) {
            stockType = Product.StockType.UNIQUE;
        } else if (stockTypeRadioId == R.id.MultipleStockRadioButton) {
            stockType = Product.StockType.MULTIPLE;
        } else {
            toastMessage.setValue("Selecione o tipo de estoque");
            return;
        }

        try {
            Product product = new Product(
                    String.valueOf(UUID.randomUUID()) , productDescription, new BigDecimal(productPrice), new BigDecimal(productCost), stockType, false, null
            );

            long result = createProductUseCase.execute(product);

            if (result > 0) {
                toastMessage.setValue("Produto Criado com Sucesso!");
            }
        } catch (Exception e) {
            toastMessage.setValue("Algo deu errado!");
            e.printStackTrace();
        }
    }
}