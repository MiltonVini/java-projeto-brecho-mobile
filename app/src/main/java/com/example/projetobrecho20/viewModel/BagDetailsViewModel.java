package com.example.projetobrecho20.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetobrecho20.model.BagWithDetails;
import com.example.projetobrecho20.useCase.GetBagDetailsUseCase;
import com.example.projetobrecho20.useCase.SetBagAsSoldUseCase;

public class BagDetailsViewModel extends ViewModel {
    private final GetBagDetailsUseCase getBagDetailsUseCase;
    private final SetBagAsSoldUseCase setBagAsSoldUseCase;
    private final MutableLiveData<BagWithDetails> bagDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void consumeToast() {
        toastMessage.setValue(null);
    }

    public BagDetailsViewModel(GetBagDetailsUseCase getBagDetailsUseCase, SetBagAsSoldUseCase setBagAsSoldUseCase) {
        this.getBagDetailsUseCase = getBagDetailsUseCase;
        this.setBagAsSoldUseCase = setBagAsSoldUseCase;
    }

    public MutableLiveData<BagWithDetails> getBagDetailsLiveData() {
        return bagDetailsLiveData;
    }

    public void loadBagDetails(String bagId) {
        BagWithDetails bagDetails = getBagDetailsUseCase.execute(bagId);
        bagDetailsLiveData.setValue(bagDetails);
    }

    public void onSetBagAsSoldButtonClick(String bagId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                setBagAsSoldUseCase.execute(bagId);

                BagWithDetails updatedBag = getBagDetailsUseCase.execute(bagId);

                bagDetailsLiveData.postValue(updatedBag);
            }
        }).start();

        toastMessage.setValue("Sacolinha Entregue!");

    }
}
