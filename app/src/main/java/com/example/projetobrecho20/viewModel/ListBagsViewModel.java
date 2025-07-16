
package com.example.projetobrecho20.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagWithCustomer;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.useCase.GetAllBagsUseCase;
import com.example.projetobrecho20.useCase.GetCustomerInfoUseCase;

import java.util.ArrayList;
import java.util.List;

public class ListBagsViewModel extends ViewModel {
    private final GetAllBagsUseCase getAllBagsUseCase;
    private final GetCustomerInfoUseCase getCustomerInfoUseCase;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<List<Bag>> bagsLiveData = new MutableLiveData<>();

    public ListBagsViewModel(GetAllBagsUseCase getAllBagsUseCase, GetCustomerInfoUseCase getCustomerInfoUseCase) {
        this.getAllBagsUseCase = getAllBagsUseCase;
        this.getCustomerInfoUseCase = getCustomerInfoUseCase;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public MutableLiveData<List<Bag>> getBagsLiveData() {
        return bagsLiveData;
    }

    public void loadBags() {
        List<Bag> bags = getAllBagsUseCase.execute();
        bagsLiveData.setValue(bags);
    }

    public List<BagWithCustomer> getBagsWithCustomers(List<Bag> bags) {
        List<BagWithCustomer> result = new ArrayList<>();

        for (Bag bag : bags) {
            Customer customer = getCustomerInfoUseCase.execute(bag.getCustomerId());
            if (customer != null) {
                result.add(new BagWithCustomer(bag, customer));
            }
        }

        return result;
    }
}