package com.example.hyunjeong.district132;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.widget.TextView;


/**
 * Created by vinu on 8/4/17.
 */

public class SortbyPrice extends SQLiteOpenHelper implements SortPost {
   // private String location;
   // private String housetype;
    //private String purpose;
    Context context;
    private Object factory;
    private Cursor cursor;

    public SortbyPrice(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*public void getValues(String[] array){

        location=array[0];
        housetype=array[1];
        purpose=array[2];
    }*/
    public Cursor sort(String location,String housetype,String purpose){
        String query = "Select " + COLUMN_POST_ID+ " AS _id,* FROM "  + TABLE_POSTS +
                " WHERE " + COLUMN_LOCATION + " = \"" + location + "\"" +
                " AND " + COLUMN_HOUSETYPE + " = \"" + housetype + "\"" +
                " AND " + COLUMN_PURPOSE + " = \"" + purpose + "\"" + " ORDER BY " + COLUMN_PRICE;
        //String query = " SELECT * FROM " + TABLE_POSTS;
        //String query = " SELECT * FROM " + TABLE_POSTS;

        SQLiteDatabase db = this.getWritableDatabase();
        // PostDBHandler dbHandler=new PostDBHandler(context,null,null,1);
        //Cursor c=dbHandler.searchPost(location,housetype,purpose);
        Cursor cursor = db.rawQuery(query, null);
       // DatabaseUtils.dumpCursorToString(cursor);
        //String location=searchResults.getLocation();
        //return location;
        // return cursor;*/
        return cursor;
    }

}
