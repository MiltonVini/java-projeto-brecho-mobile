package com.example.projetobrecho20.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetobrecho20.database.AppDatabase;
import com.example.projetobrecho20.model.BagItem;

import java.util.ArrayList;
import java.util.UUID;

public class BagItemsRepository {
    private final AppDatabase appDatabase;

    public BagItemsRepository(Context context) {
        appDatabase = new AppDatabase(context);
    }

    public long insertProductsInBag(BagItem bagItems) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", String.valueOf(UUID.randomUUID()));
        contentValues.put("bag_id", bagItems.getBagId());
        contentValues.put("product_id", bagItems.getProductId());
        contentValues.put("quantity", 0);

        Log.d("DEBUG", "Inserindo BagItem com bag_id: " + bagItems.getBagId());

        SQLiteDatabase db = appDatabase.getWritableDatabase();
        long newRowId = db.insert("bag_items", null, contentValues);

        db.close();
        Log.d("DEBUG", "Produto inserido na sacola" + newRowId);
        return newRowId;
    }

    public ArrayList<BagItem> getAllProductsInBag(String bagId) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();

        String selection = "bag_id =?";
        String[] selectionArgs = {bagId};

        ArrayList<BagItem> bagItems = new ArrayList<>();

        Cursor cursor = db.query("bag_items", null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String foundBagId = cursor.getString(cursor.getColumnIndexOrThrow("bag_id"));
                String productId = cursor.getString(cursor.getColumnIndexOrThrow("product_id"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                Log.d("DEBUG", productId);

                bagItems.add(new BagItem(id, productId, quantity, foundBagId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return bagItems;
    }
}
