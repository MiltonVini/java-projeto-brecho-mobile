package com.example.projetobrecho20.view.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobrecho20.R;
import com.example.projetobrecho20.model.Product;

import java.util.List;

public class BagDetailsProductAdapter extends RecyclerView.Adapter<BagDetailsProductAdapter.ViewHolder> {
    private final List<Product> productList;

    public BagDetailsProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_products_list_bag_details, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        Log.d("DEBUG", "produto: " + product.getId());
        holder.bagDetailsProductDescriptionTextView.setText(product.getDescription());
        holder.bagDetailsProductPriceTextView.setText(
                holder.itemView.getContext().getString(R.string.price_format, product.getPrice())
        );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bagDetailsProductDescriptionTextView;
        TextView bagDetailsProductPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            bagDetailsProductDescriptionTextView = itemView.findViewById(R.id.bagDetailsProductDescriptionTextView);
            bagDetailsProductPriceTextView = itemView.findViewById(R.id.bagDetailsProductPriceTextView);
        }
    }
}
