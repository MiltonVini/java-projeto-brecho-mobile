package com.example.projetobrecho20.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetobrecho20.databinding.FragmentRegisterCustomerBinding;
import com.example.projetobrecho20.viewModel.RegisterCustomerViewModel;
import com.example.projetobrecho20.viewModel.RegisterCustomerViewModelFactory;

public class RegisterCustomerFragment extends Fragment {

private FragmentRegisterCustomerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        RegisterCustomerViewModel registerCustomerViewModel =
                new ViewModelProvider(this, new RegisterCustomerViewModelFactory(requireContext())).get(RegisterCustomerViewModel.class);

        binding = FragmentRegisterCustomerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.registerCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.clientNameEditText.getText().toString();
                String instagramUser = binding.clientInstagramUserEditText.getText().toString();
                String email = binding.clientEmailEditText.getText().toString();

                registerCustomerViewModel.onRegisterCustomerButtonClick(name, instagramUser, email);

                binding.clientEmailEditText.setText("");
                binding.clientNameEditText.setText("");
                binding.clientInstagramUserEditText.setText("");

            }
        });

        registerCustomerViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                registerCustomerViewModel.consumeToast();
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