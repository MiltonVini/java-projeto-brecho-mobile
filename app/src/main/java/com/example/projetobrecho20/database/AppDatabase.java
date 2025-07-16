package com.example.projetobrecho20.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "brecho.sqlite";
    private static final int DB_VERSION = 3;

    public AppDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCustomersTableQuery = "CREATE TABLE customers (\n" +
                "    id TEXT PRIMARY KEY," +
                "    name TEXT NOT NULL," +
                "    instagram_user TEXT," +
                "    email TEXT" +
                ")";
        String createProductsTableQuery = "CREATE TABLE products (" +
                "    id TEXT PRIMARY KEY," +
                "    description TEXT NOT NULL," +
                "    price REAL NOT NULL," +
                "    cost REAL NOT NULL," +
                "    stock_type TEXT NOT NULL CHECK (stock_type IN ('UNIQUE', 'MULTIPLE'))," +
                "    is_sold INTEGER NOT NULL CHECK (is_sold IN (0, 1))," +
                "    sold_at TEXT " +
                ")";
        String createBagsTableQuery = "CREATE TABLE bags (" +
                "    id TEXT PRIMARY KEY," +
                "    customer_id TEXT NOT NULL," +
                "    is_delivered INTEGER NOT NULL CHECK (is_delivered IN (0, 1))," +
                "    created_at TEXT NOT NULL, " +
                "    delivered_at TEXT, " +
                "    FOREIGN KEY (customer_id) REFERENCES customers(id)" +
                ")";
        String createBagItemsTableQuery = "CREATE TABLE bag_items (" +
                "    id TEXT PRIMARY KEY," +
                "    bag_id TEXT NOT NULL," +
                "    product_id TEXT NOT NULL," +
                "    quantity INTEGER NOT NULL," +
                "    FOREIGN KEY (bag_id) REFERENCES bags(id)," +
                "    FOREIGN KEY (product_id) REFERENCES products(id)" +
                ")";

        db.execSQL(createCustomersTableQuery);
        db.execSQL(createProductsTableQuery);
        db.execSQL(createBagsTableQuery);
        db.execSQL(createBagItemsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bag_items");
        db.execSQL("DROP TABLE IF EXISTS bags");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS customers");
        onCreate(db);
    }
}

