package com.example.hyunjeong.district132;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class PostDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PostDB.db";
    private static final String TABLE_POSTS = "Posts";

    public static final String COLUMN_POST_ID = "post_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_HOUSETYPE = "housetype";
    public static final String COLUMN_PURPOSE = "purpose";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_NO_OF_ROOMS = "no_of_rooms";
    public static final String COLUMN_FURNISHING = "furnishing";
    public static final String COLUMN_FACILITIES = "facilities";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_ADDRESS="address";
    public PostDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POSTS + "(" + COLUMN_POST_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USERNAME + " VARCHAR(16)," + COLUMN_LOCATION + " VARCHAR(20)," + COLUMN_HOUSETYPE + " VARCHAR(20),"
                + COLUMN_PURPOSE + " TEXT," + COLUMN_PRICE + " DECIMAL(7,2)," + COLUMN_NO_OF_ROOMS + " INTEGER," +
                COLUMN_FURNISHING + " BOOLEAN," + COLUMN_DESC + " VARCHAR(100)," +
                COLUMN_SIZE + " DECIMAL(4,2)," + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        onCreate(db);
    }

    public void addPost(PostDB post) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_ID, post.getPost_id());
        values.put(COLUMN_USERNAME, post.getUsername());
        values.put(COLUMN_LOCATION, post.getLocation());
        values.put(COLUMN_HOUSETYPE, post.getHousetype());
        values.put(COLUMN_PURPOSE, post.getPurpose());
        values.put(COLUMN_PRICE, post.getPrice());
        values.put(COLUMN_NO_OF_ROOMS, post.getNo_of_rooms());
        values.put(COLUMN_FURNISHING, post.getFurnishing());
        values.put(COLUMN_FACILITIES, post.getFacilities());
        values.put(COLUMN_DESC, post.getDesc());
        values.put(COLUMN_SIZE, post.getSize());
        values.put(COLUMN_ADDRESS, post.getAddress());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_POSTS, null, values);
        db.close();
    }

    public Cursor searchPost(String location, String housetype, String purpose) {
     String query = "Select " + COLUMN_POST_ID + " AS _id,* FROM "  + TABLE_POSTS +
                " WHERE " + COLUMN_LOCATION + " = \"" + location + "\"" +
                " AND " + COLUMN_HOUSETYPE + " = \"" + housetype + "\"" +
                " AND " + COLUMN_PURPOSE + " = \"" + purpose + "\"";

        //String query = " SELECT * FROM " + TABLE_POSTS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        //DatabaseUtils.dumpCursorToString(cursor);
        return cursor;
    }

    public boolean deletePost(int post_id) {
        boolean result = false;

        String query = "Select * FROM " + TABLE_POSTS +
                " WHERE " + COLUMN_POST_ID + " =  \"" + post_id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        PostDB post = new PostDB();

        if (cursor.moveToFirst()) {
            post.setPost_id(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_POSTS, COLUMN_POST_ID + " = ?",
                    new String[] { String.valueOf(post.getPost_id()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public Cursor getlocbyid(int postid)
    {
        String query = "Select * FROM " + TABLE_POSTS +
                " WHERE " + COLUMN_POST_ID + " =  \"" + postid + "\"";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }

}
