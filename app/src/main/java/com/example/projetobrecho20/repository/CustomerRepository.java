package com.example.projetobrecho20.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobrecho20.database.AppDatabase;
import com.example.projetobrecho20.model.Customer;

import java.util.ArrayList;

public class CustomerRepository {
    private final AppDatabase appDatabase;

    public CustomerRepository(Context context) {
        appDatabase = new AppDatabase(context);
    }

    public long insert(Customer customer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", customer.getId());
        contentValues.put("name", customer.getName());
        contentValues.put("instagram_user", customer.getInstagram_user());
        contentValues.put("email", customer.getEmail());

        SQLiteDatabase db = appDatabase.getWritableDatabase();
        long newRowId = db.insert("customers", null, contentValues);

        db.close();
        return newRowId;

    }

    public ArrayList<Customer> getAllCustomers() {
        SQLiteDatabase db = appDatabase.getReadableDatabase();
        Cursor cursor = db.query("customers", null, null, null, null, null, null);

        ArrayList<Customer> customers = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String instagramUser = cursor.getString(cursor.getColumnIndexOrThrow("instagram_user"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

                customers.add(new Customer(
                        id, name, instagramUser, email
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return customers;
    }

    public Customer getCustomerById(String customerId) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = {customerId};

        Cursor cursor = db.query("customers", null, selection, selectionArgs, null, null, null);
        Customer customer = null;

        if (cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String instagramUser = cursor.getString(cursor.getColumnIndexOrThrow("instagram_user"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            customer = new Customer(id, name, instagramUser, email);
        }

        cursor.close();
        return customer;
    }


}
