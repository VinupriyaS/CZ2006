package com.example.hyunjeong.district132;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vinu on 8/4/17.
 */

public class SortbySize extends SQLiteOpenHelper implements SortPost {
    private String location;
    private String housetype;
    private String purpose;
    Context context;
    private Object factory;
    private Cursor cursor;

    public SortbySize(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
   /* public void getValues(String[] array){
        location=array[0];
        housetype=array[1];
        purpose=array[2];
    }*/
    public Cursor sort(String location,String housetype,String purpose){
        String query = "Select " + COLUMN_POST_ID+ " AS _id,* FROM "  + TABLE_POSTS +
                " WHERE " + COLUMN_LOCATION + " = \"" + location + "\"" +
                " AND " + COLUMN_HOUSETYPE + " = \"" + housetype + "\"" +
                " AND " + COLUMN_PURPOSE + " = \"" + purpose + "\"" + " ORDER BY " + COLUMN_SIZE;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return  cursor;
    }

}


