package com.imagecolletorsllc.imagecollectors.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nturner on 9/12/17.
 */

public class PhotoShootsTable extends DbOperator {
    //Constructor to call the PhotoShootsTable super class
    public PhotoShootsTable(Context context) {
        super(context);
    }
    //Set variables to the column names
    private static final String COLid = "id";
    private static final String COLName = "clientName";
    private static final String COLEmail = "clientEmail";
    private static final String COLAddress = "clientAddress";

    //Add client method
    public void addClient(String clientsName, String clientsEmail, String clientsAddress){
        //initializes the database
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a map of values to insert into the database
        ContentValues values = new ContentValues();
        //Add to map of values
        values.put(COLName, clientsName);
        values.put(COLEmail, clientsEmail);
        values.put(COLAddress, clientsAddress);
        //Insert the items into the database, and return the new "id" of the row
        long newRowId = db.insert(DbOperator.FIRST_TABLE_NAME, null, values);
        db.close();
    }
    public List<PhotoShootData> getFirstTableDataList() {
        //cursor is where you are at in the database while grabbing the sections of data
        List<PhotoShootData> firstTableDataList = new ArrayList<>();
        String refQuery = "Select * From " + DbOperator.FIRST_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(refQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    PhotoShootData itemData = new PhotoShootData();
                    itemData.setPhotoshootId(cursor.getLong(0));
                    itemData.setClientName(cursor.getString(1));
                    itemData.setClientEmail(cursor.getString(2));
                    itemData.setClientAddress(cursor.getString(3));
                    firstTableDataList.add(itemData);
                } while (cursor.moveToNext());
            }
        } finally {

            db.close();
        }
        return firstTableDataList;
    }


}
