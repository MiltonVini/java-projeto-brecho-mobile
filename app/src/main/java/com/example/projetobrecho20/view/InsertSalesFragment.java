package com.example.projetobrecho20.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.projetobrecho20.databinding.FragmentInsertSalesBinding;
import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagItem;
import com.example.projetobrecho20.model.Customer;
import com.example.projetobrecho20.model.Product;
import com.example.projetobrecho20.view.adpater.ProductAdapter;
import com.example.projetobrecho20.viewModel.InsertSalesViewModel;
import com.example.projetobrecho20.viewModel.InsertSalesViewModelFactory;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class InsertSalesFragment extends Fragment {
    private FragmentInsertSalesBinding binding;
    private InsertSalesViewModel insertSalesViewModel;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        insertSalesViewModel = new ViewModelProvider(this, new InsertSalesViewModelFactory(requireContext())).get(InsertSalesViewModel.class);

        binding = FragmentInsertSalesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

            insertSalesViewModel.getCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
                @Override
                public void onChanged(List<Customer> customers) {
                    Customer hintCustomer = new Customer(
                            "0", null, "Selecione o cliente", null
                    );
                    customers.add(0, hintCustomer);

                    ArrayAdapter<Customer> adapter = new ArrayAdapter<>(
                            requireContext(), android.R.layout.simple_spinner_item, customers
                    );

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.customersSpinner.setAdapter(adapter);

                    binding.customersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Customer selectedCustomer = (Customer) parent.getItemAtPosition(position);
                            insertSalesViewModel.loadCustomerActiveBag(selectedCustomer);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            insertSalesViewModel.loadCustomerActiveBag(null);
                        }
                    });
                }
            });

        binding.productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        insertSalesViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                adapter = new ProductAdapter(productList);
                binding.productRecyclerView.setAdapter(adapter);
            }
        });

        binding.createBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer selectedCustomer = (Customer) binding.customersSpinner.getSelectedItem();
                insertSalesViewModel.createBag(selectedCustomer);

                insertSalesViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
                    if (message != null) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        insertSalesViewModel.consumeToast();
                    }
                });
            }
        });


        insertSalesViewModel.getCustomerActiveBag().observe(getViewLifecycleOwner(), new Observer<Bag>() {
            @Override
            public void onChanged(Bag bag) {
                int customerPosition = binding.customersSpinner.getSelectedItemPosition();
                boolean isCustomerSelected = customerPosition != 0;
                boolean customerHasBag = bag != null;

                binding.hasActiveBagTextView.setVisibility(isCustomerSelected ? View.VISIBLE : View.INVISIBLE);

                if (isCustomerSelected) {
                    binding.hasActiveBagTextView.setText(customerHasBag ? "Cliente possui uma sacolinha ativa!" : "Cliente não possui uma sacolinha ativa!");

                    binding.createBagButton.setEnabled(!customerHasBag);
                } else {
                    binding.createBagButton.setEnabled(false);
                }

            }
        });

        binding.insertSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clientSelectedPosition = binding.customersSpinner.getSelectedItemPosition();

                if (clientSelectedPosition == 0) {
                    Log.w("DEBUG_SELECTION", "Nenhum cliente válido selecionado");
                    return;
                }

                Customer selectedCustomer = (Customer) binding.customersSpinner.getSelectedItem();
                Bag customerActiveBag = insertSalesViewModel.getCustomerActiveBag().getValue();

                if (customerActiveBag == null) {
                    Log.w("DEBUG_SELECTION", "Cliente não possui sacolinha ativa.");
                    return;
                }

                Set<String> selectedProductIds = adapter.getSelectedProductIds();

                Log.d("DEBUG_SELECTION", "Cliente selecionado: " + selectedCustomer.getInstagram_user());
                Log.d("DEBUG_SELECTION", "ID do cliente: " + selectedCustomer.getId());
                Log.d("DEBUG_SELECTION", "Sacolinha ativa: " + customerActiveBag.getId());
                Log.d("DEBUG_SELECTION", "Produtos selecionados: " + selectedProductIds);

                for (String id : selectedProductIds ) {
                    BagItem newBagItem = new BagItem(String.valueOf(UUID.randomUUID()), id, 0, String.valueOf(customerActiveBag.getId()));

                    insertSalesViewModel.insertProductsInBag(newBagItem);

                    insertSalesViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
                        if (message != null) {
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            insertSalesViewModel.consumeToast();
                        }
                    });
                }

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        insertSalesViewModel.loadCustomers();
        insertSalesViewModel.loadProducts();
    }
}