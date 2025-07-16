package com.example.projetobrecho20.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagItem;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.useCase.CreateBagUseCase;
import com.example.projetobrecho20.useCase.GetActiveBagByCustomerIdUseCase;
import com.example.projetobrecho20.useCase.GetAllCustomersUseCase;
import com.example.projetobrecho20.useCase.GetAllProductsUseCase;
import com.example.projetobrecho20.useCase.InsertProductsInBagUseCase;

import java.util.List;

public class InsertSalesViewModel extends ViewModel {
    private final InsertProductsInBagUseCase insertProductsInBagUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final CreateBagUseCase createBagUseCase;
    private final GetActiveBagByCustomerIdUseCase getActiveBagByCustomerIdUseCase;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<List<Customer>> customersLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Bag> customerActiveBagLiveData = new MutableLiveData<>();


    public InsertSalesViewModel(InsertProductsInBagUseCase insertProductsInBagUseCase,
                                GetAllCustomersUseCase getAllCustomersUseCase,
                                GetAllProductsUseCase getAllProductsUseCase,
                                CreateBagUseCase createBagUseCase,
                                GetActiveBagByCustomerIdUseCase getActiveBagByCustomerIdUseCase) {
        this.insertProductsInBagUseCase = insertProductsInBagUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.createBagUseCase = createBagUseCase;
        this.getActiveBagByCustomerIdUseCase = getActiveBagByCustomerIdUseCase;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void consumeToast() {
        toastMessage.setValue(null);
    }

    public LiveData<List<Customer>> getCustomers() {
        return customersLiveData;
    }

    public MutableLiveData<List<Product>> getProducts() {
        return productsLiveData;
    }

    public MutableLiveData<Bag> getCustomerActiveBag() {
        return customerActiveBagLiveData;
    }

    public void loadCustomers() {
        List<Customer> customers = getAllCustomersUseCase.execute();
        customersLiveData.setValue(customers);
    }

    public void loadProducts() {
        List<Product> products = getAllProductsUseCase.execute();
        productsLiveData.setValue(products);
    }

    public void loadCustomerActiveBag(Customer customer) {
        Bag customerActiveBag = getActiveBagByCustomerIdUseCase.execute(customer);
        customerActiveBagLiveData.setValue(customerActiveBag);

    }

    public void createBag(Customer customer) {
        createBagUseCase.execute(customer);

        loadCustomerActiveBag(customer);

        toastMessage.setValue("Sacolinha criada com sucesso!");
    }

    public void insertProductsInBag(BagItem bagItem) {
        insertProductsInBagUseCase.execute(bagItem);
        toastMessage.setValue("Venda registrada com sucesso!");
    }

}