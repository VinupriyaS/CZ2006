package com.example.hyunjeong.district132;
import android.database.Cursor;

/**
 * Created by vinu on 8/4/17.
 */

public interface SortPost {
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "PostDB.db";
    static final String TABLE_POSTS = "Posts";




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

    public Cursor sort(String location,String housetype,String purpose);
    //public void getValues(String[] array);
}
