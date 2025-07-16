package com.example.projetobrecho20.useCase;

import android.content.Context;

import com.example.projetobrecho20.model.BagItem;
import com.example.projetobrecho20.repository.BagItemsRepository;
import com.example.projetobrecho20.repository.ProductRepository;

public class InsertProductsInBagUseCase {
    private final BagItemsRepository bagItemsRepository;
    private final ProductRepository productRepository;

    public InsertProductsInBagUseCase(Context context) {
        this.bagItemsRepository = new BagItemsRepository(context);
        this.productRepository = new ProductRepository(context);
    }

    public void execute(BagItem bagItem) {
        bagItemsRepository.insertProductsInBag(bagItem);
        productRepository.setProductAsSold(bagItem.getProductId());
    }
}
