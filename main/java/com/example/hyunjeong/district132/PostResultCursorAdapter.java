package com.example.hyunjeong.district132;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class PostResultCursorAdapter extends CursorAdapter {
    private final LayoutInflater mInflater;

    public PostResultCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        //Get inflater
        mInflater = LayoutInflater.from(context);

    }

    // The newView method is used to inflate a new view and return it,
    // don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //return LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
        return mInflater.inflate(R.layout.list_view, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textViewID = (TextView) view.findViewById(R.id.postID);
        TextView textViewUsername = (TextView) view.findViewById(R.id.postUsername);
        TextView textViewLocation = (TextView) view.findViewById(R.id.postLocation);
        TextView textViewPrice = (TextView) view.findViewById(R.id.postPrice);
        TextView textViewSize = (TextView) view.findViewById(R.id.postSize);

        // Extract properties from cursor
        String id = cursor.getString( cursor.getColumnIndex( PostDBHandler.COLUMN_POST_ID ));
        String name = cursor.getString( cursor.getColumnIndex( PostDBHandler.COLUMN_USERNAME ));
        String loc = cursor.getString( cursor.getColumnIndex( PostDBHandler.COLUMN_LOCATION ));
        String px = cursor.getString( cursor.getColumnIndex( PostDBHandler.COLUMN_PRICE ));
        String sz = cursor.getString( cursor.getColumnIndex( PostDBHandler.COLUMN_SIZE ));

        // Populate fields with extracted properties
        textViewID.setText(id);
        textViewUsername.setText(name);
        textViewLocation.setText(loc);
        textViewPrice.setText(px);
        textViewSize.setText(sz);
    }
}
