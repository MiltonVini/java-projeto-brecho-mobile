package com.example.projetobrecho20.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projetobrecho20.databinding.FragmentListBagsBinding;
import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.BagWithCustomer;
import com.example.projetobrecho20.view.adpater.BagAdapter;
import com.example.projetobrecho20.viewModel.ListBagsViewModel;
import com.example.projetobrecho20.viewModel.ListBagsViewModelFactory;

import java.util.List;

public class ListBagsFragment extends Fragment {

private FragmentListBagsBinding binding;
private ListBagsViewModel listBagsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        listBagsViewModel =
                new ViewModelProvider(this, new ListBagsViewModelFactory(requireContext())).get(ListBagsViewModel.class);

        binding = FragmentListBagsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.listBagsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        listBagsViewModel.getBagsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Bag>>() {
            @Override
            public void onChanged(List<Bag> bagList) {
                List<BagWithCustomer> completeBagList = listBagsViewModel.getBagsWithCustomers(bagList);
                BagAdapter adapter = new BagAdapter(completeBagList);
                binding.listBagsRecyclerView.setAdapter(adapter);
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
        listBagsViewModel.loadBags();
    }
}