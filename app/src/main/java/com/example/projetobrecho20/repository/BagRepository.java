package com.example.projetobrecho20.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetobrecho20.database.AppDatabase;
import com.example.projetobrecho20.model.Bag;
import com.example.projetobrecho20.model.Customer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class BagRepository {
    private final AppDatabase appDatabase;

    public BagRepository(Context context) {
        this.appDatabase = new AppDatabase(context);
    }

    public long create(Customer customer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", String.valueOf(UUID.randomUUID()));
        contentValues.put("customer_id", customer.getId());
        contentValues.put("is_delivered", false);
        contentValues.put("created_at", getCurrentDateTime());

        SQLiteDatabase db = appDatabase.getWritableDatabase();
        long newRowId = db.insert("bags", null, contentValues);

        db.close();
        return  newRowId;
    }

    public ArrayList<Bag> getAllBags() {
        SQLiteDatabase db = appDatabase.getReadableDatabase();
        Cursor cursor = db.query("bags", null, null, null, null, null, null, null);
        ArrayList<Bag> bags = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String customerId = cursor.getString(cursor.getColumnIndexOrThrow("customer_id"));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow("created_at"));
                String deliveredAt = cursor.getString(cursor.getColumnIndexOrThrow("delivered_at"));
                int isDeliveredInt = cursor.getInt(cursor.getColumnIndexOrThrow("is_delivered"));
                boolean isDelivered = isDeliveredInt == 1;

                bags.add(new Bag(id, customerId, isDelivered, createdAt, deliveredAt));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return bags;
    }

    public Bag getActiveBagByCustomerId(Customer customer) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();

        String selection = "customer_id = ? AND is_delivered = 0";
        String[] selectionArgs = {customer.getId()};

        Cursor cursor = db.query("bags", null, selection, selectionArgs, null, null, null);

        Bag customerActiveBag = null;

        if (cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String customerId = cursor.getString(cursor.getColumnIndexOrThrow("customer_id"));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow("created_at"));
            String deliveredAt = cursor.getString(cursor.getColumnIndexOrThrow("delivered_at"));
            int isDeliveredInt = cursor.getInt(cursor.getColumnIndexOrThrow("is_delivered"));
            boolean isDelivered = isDeliveredInt == 1;

            customerActiveBag = new Bag(id, customerId, isDelivered, createdAt, deliveredAt);
        }
        cursor.close();
        return customerActiveBag;
    }

    public Bag getBagById(String bagId) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();

        String selection = "id =?";
        String[] selectionArgs = {bagId};

        Cursor cursor = db.query("bags", null, selection, selectionArgs, null, null, null);

        Bag bag = null;

        if (cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String customerId = cursor.getString(cursor.getColumnIndexOrThrow("customer_id"));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow("created_at"));
            String deliveredAt = cursor.getString(cursor.getColumnIndexOrThrow("delivered_at"));
            int isDeliveredInt = cursor.getInt(cursor.getColumnIndexOrThrow("is_delivered"));
            boolean isDelivered = isDeliveredInt == 1;

            bag = new Bag(id, customerId, isDelivered, createdAt, deliveredAt);
        }
        cursor.close();
        return bag;
    }

    public void setBagAsSold(String badId) {
        SQLiteDatabase db = appDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("is_delivered", 1);
        contentValues.put("delivered_at", getCurrentDateTime());

        String selection = "id = ?";
        String[] selectionArgs = { badId };

        db.update("bags", contentValues, selection, selectionArgs);
        db.close();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return currentDateTime.format(new Date());
    }
}
