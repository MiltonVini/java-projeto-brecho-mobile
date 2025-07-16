package com.example.projetobrecho20.useCase;

import android.content.Context;
import android.util.Log;

import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagItem;
import com.example.projetobrecho20.model.BagWithDetails;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.repository.BagItemsRepository;
import com.example.projetobrecho20.repository.BagRepository;
import com.example.projetobrecho20.repository.CustomerRepository;
import com.example.projetobrecho20.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class GetBagDetailsUseCase {
    private final BagRepository bagRepository;
    private final CustomerRepository customerRepository;
    private final BagItemsRepository bagItemsRepository;
    private final ProductRepository productRepository;

    public GetBagDetailsUseCase(Context context) {
        this.bagRepository = new BagRepository(context);
        this.customerRepository = new CustomerRepository(context);
        this.bagItemsRepository = new BagItemsRepository(context);
        this.productRepository = new ProductRepository(context);
    }

    public BagWithDetails execute(String bagId) {
        Bag bag = bagRepository.getBagById(bagId);
        Customer customer = customerRepository.getCustomerById(bag.getCustomerId());
        List<BagItem> bagItems = bagItemsRepository.getAllProductsInBag(bagId);
        Log.d("DEBUG", "Itens encontrados na sacola: " + bagItems.size());
        ArrayList<Product> products = new ArrayList<>();

        for (BagItem product : bagItems) {
            Log.d("DEBUG NO USECASE", "Produto: " + product.getProductId());
            Product productDetails = productRepository.getProductById(product.getProductId());
            Log.d("DEBUG NO USECASE", "Produto: " + productDetails.getDescription());
            products.add(productDetails);
        }

        BagWithDetails bagWithDetails = new BagWithDetails(bag, customer, bagItems, products);

        return bagWithDetails;
    }
}
