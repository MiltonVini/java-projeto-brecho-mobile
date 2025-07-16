package com.example.projetobrecho20.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetobrecho20.R;
import com.example.projetobrecho20.databinding.FragmentBagDetailsBinding;
import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagWithDetails;
import com.example.projetobrecho20.view.adpater.BagDetailsProductAdapter;
import com.example.projetobrecho20.viewModel.BagDetailsViewModel;
import com.example.projetobrecho20.viewModel.BagDetailsViewModelFactory;

public class BagDetailsFragment extends Fragment {
    private FragmentBagDetailsBinding binding;
    private BagDetailsProductAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BagDetailsViewModel bagDetailsViewModel =  new ViewModelProvider(this, new BagDetailsViewModelFactory(requireContext())).get(BagDetailsViewModel.class);
        binding = FragmentBagDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String bagId = getArguments().getString("bagId");
        Log.d("BagDetailsFragment", "Bag ID recebido: " + bagId);


        bagDetailsViewModel.loadBagDetails(bagId);

        bagDetailsViewModel.getBagDetailsLiveData().observe(getViewLifecycleOwner(), new Observer<BagWithDetails>() {
            @Override
            public void onChanged(BagWithDetails bagWithDetails) {
                binding.bagDetailsClientInstagramUserTextView.setText(bagWithDetails.getCustomer().getInstagram_user());
                binding.bagDetailsBagStatusTextView.setText(bagWithDetails.getBag().getDelivered() ? "Entregue" : "Entrega a Combinar");

                binding.bagDetailsProductRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                adapter = new BagDetailsProductAdapter(bagWithDetails.getProductList());
                binding.bagDetailsProductRecyclerView.setAdapter(adapter);

                binding.setAsSoldButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bagDetailsViewModel.onSetBagAsSoldButtonClick(bagId);
                        bagDetailsViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
                            if (message != null) {
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                bagDetailsViewModel.consumeToast();
                            }
                        });
                    }
                });
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
    }
}