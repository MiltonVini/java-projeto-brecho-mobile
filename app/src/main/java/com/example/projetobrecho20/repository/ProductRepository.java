package com.example.projetobrecho20.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetobrecho20.database.AppDatabase;
import com.example.projetobrecho20.model.Product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProductRepository {
    private final AppDatabase appDatabase;

    public ProductRepository(Context context) {
        appDatabase = new AppDatabase(context);
    }

    public long insert(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", product.getId());
        contentValues.put("description", product.getDescription());
        contentValues.put("price", String.valueOf(product.getPrice()));
        contentValues.put("cost", String.valueOf(product.getCost()));
        contentValues.put("stock_type", String.valueOf(product.getStockType()));
        contentValues.put("is_sold", 0);

        SQLiteDatabase db = appDatabase.getWritableDatabase();
        long newRowId = db.insert("products", null, contentValues);

        return newRowId;
    }

    public ArrayList<Product> getAllProducts() {
        SQLiteDatabase db = appDatabase.getReadableDatabase();
        String selection = "is_sold =?";
        String[] selectionArgs = { "0" };
        Cursor cursor = db.query("products", null, selection, selectionArgs, null, null, null, null);

        ArrayList<Product> products = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String cost = cursor.getString(cursor.getColumnIndexOrThrow("cost"));
                Product.StockType stockType = Product.StockType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("stock_type")));
                int isSoldInt = cursor.getInt(cursor.getColumnIndexOrThrow("is_sold"));
                String soldAt = cursor.getString(cursor.getColumnIndexOrThrow("sold_at"));
                boolean isSold = isSoldInt == 1;

                products.add(
                        new Product(id, description, new BigDecimal(price), new BigDecimal(cost), stockType, isSold, soldAt)
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
        return products;
    }

    public Product getProductById(String productId) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();
        String selection = "id =?";
        String[] selectionArgs = {productId};
        Cursor cursor = db.query("products", null, selection, selectionArgs, null, null, null, null);

        Product product = null;

        if (cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            String cost = cursor.getString(cursor.getColumnIndexOrThrow("cost"));
            Product.StockType stockType = Product.StockType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("stock_type")));
            int isSoldInt = cursor.getInt(cursor.getColumnIndexOrThrow("is_sold"));
            String soldAt = cursor.getString(cursor.getColumnIndexOrThrow("sold_at"));
            boolean isSold = isSoldInt == 1;

            product = (
                    new Product(id, description, new BigDecimal(price), new BigDecimal(cost), stockType, isSold, soldAt)
            );
        }

        cursor.close();
        return product;
    }

    public void setProductAsSold(String productId) {
        SQLiteDatabase db = appDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("is_sold", 1);
        contentValues.put("sold_at", getCurrentDateTime());

        String selection = "id = ?";
        String[] selectionArgs = { productId };

        db.update("products", contentValues, selection, selectionArgs);
        db.close();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return currentDateTime.format(new Date());
    }


}

