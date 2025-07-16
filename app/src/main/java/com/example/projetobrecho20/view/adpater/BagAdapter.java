package com.example.projetobrecho20.view.adpater;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobrecho20.R;
import com.example.projetobrecho20.model.BagWithCustomer;

import java.util.List;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.ViewHolder> {
    private final List<BagWithCustomer> bagListWithCustomerInfo;
    public BagAdapter(List<BagWithCustomer> bagListWithCustomerInfo) {
        this.bagListWithCustomerInfo = bagListWithCustomerInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.recyclerview_bag_item, parent, false);

        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BagWithCustomer bag = bagListWithCustomerInfo.get(position);
        holder.clientInstagramUserTextView.setText(bag.getCustomer().getInstagram_user());
        holder.bagStatusTextView.setText(bag.getBag().getDelivered() ? "Entregue" : "Entrega a Combinar");
        holder.bagCreationDateTextView.setText(bag.getBag().getCreatedAt());
        holder.bagDeliveredDateTextView.setText(bag.getBag().getDeliveredAt() != null ? bag.getBag().getDeliveredAt() : "-");

        holder.bagLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("bagId", bag.getBag().getId());

                Navigation.findNavController(v).navigate(R.id.nav_bag_details, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bagListWithCustomerInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bagLinearLayout;
        TextView clientInstagramUserTextView;
        TextView bagStatusTextView;
        TextView bagCreationDateTextView;
        TextView bagDeliveredDateTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bagLinearLayout = itemView.findViewById(R.id.bagLinearLayout);
            clientInstagramUserTextView = itemView.findViewById(R.id.clientInstagramUserTextView);
            bagStatusTextView = itemView.findViewById(R.id.bagStatusTextView);
            bagCreationDateTextView = itemView.findViewById(R.id.bagCreationDateTextView);
            bagDeliveredDateTextView = itemView.findViewById(R.id.bagDeliveredDateTextView);
        }
    }
}
