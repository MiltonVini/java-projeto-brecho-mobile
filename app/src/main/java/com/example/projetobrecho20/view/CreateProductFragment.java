package com.example.projetobrecho20.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.databinding.FragmentCreateProductBinding;
import com.example.projetobrecho20.viewModel.CreateProductViewModel;
import com.example.projetobrecho20.viewModel.CreateProductViewModelFactory;

public class CreateProductFragment extends Fragment {

private FragmentCreateProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        CreateProductViewModel createProductViewModel =
                new ViewModelProvider(this, new CreateProductViewModelFactory(requireContext())).get(CreateProductViewModel.class);

        binding = FragmentCreateProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productDescription = binding.productDescriptionEditText.getText().toString();
                String productPrice = binding.productPriceEditText.getText().toString();
                String productCost = binding.productCostEditText.getText().toString();
                int productStockType = binding.stockTypeRadioGroup.getCheckedRadioButtonId();

                createProductViewModel.onCreateProductButtonClick(productDescription, productPrice, productCost, productStockType);

                binding.productDescriptionEditText.setText("");
                binding.productPriceEditText.setText("");
                binding.productCostEditText.setText("");
                binding.stockTypeRadioGroup.clearCheck();
            }
        });

        createProductViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                createProductViewModel.consumeToast();
            }
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}