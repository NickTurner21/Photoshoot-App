package com.imagecolletorsllc.imagecollectors.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nturner on 9/11/17.
 */

public class DbOperator extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "photoshootapp.db";
    //The clients information table name
    protected static final String FIRST_TABLE_NAME = "photoshoots";
    //Command to create table in database
    public static final String CREATE_FIRST_TABLE = "CREATE TABLE IF NOT EXISTS " + FIRST_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, clientName TEXT NOT NULL, clientEmail TEXT, clientAddress TEXT);";

    //Constructor to call the SQLiteOpenHelper super class
    public DbOperator(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //OnCreate method (when the class is called)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executes the create table command
        db.execSQL(CREATE_FIRST_TABLE);
        //Closes db connection
        db.close();
    }
    //onUpgrade method for when the database version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //THIS WILL BE EXECUTED WHEN YOU UPDATED VERSION OF DATABASE_VERSION
        //YOUR DROP AND CREATE QUERIES
    }
}
