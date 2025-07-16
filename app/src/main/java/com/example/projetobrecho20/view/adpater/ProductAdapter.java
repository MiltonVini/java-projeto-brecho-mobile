package com.example.projetobrecho20.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobrecho20.R;
import com.example.projetobrecho20.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final List<Product> productList;
    private final Set<String> selectedProducts = new HashSet<>();
    public ProductAdapter(List<Product> productsList) {
        this.productList = productsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_products_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productPriceTextView.setText(
                holder.itemView.getContext().getString(R.string.price_format, product.getPrice())
        );
        holder.productCheckBox.setChecked(selectedProducts.contains(product.getId()));

        holder.productCheckBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                selectedProducts.add(product.getId());
            } else {
                selectedProducts.remove(product.getId());
            }
        }));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public Set<String> getSelectedProductIds() {
        return selectedProducts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox productCheckBox;
        TextView productDescriptionTextView;
        TextView productPriceTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productCheckBox = itemView.findViewById(R.id.productCheckBox);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}
