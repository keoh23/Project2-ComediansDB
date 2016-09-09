package com.example.owen.comediansdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //getting instance from database
        SUCDatabaseHelper helper = SUCDatabaseHelper.getInstance(this);
        int id = getIntent().getIntExtra("id",0);

        if(id >= 0) {
            Cursor cursor = helper.detailsComedian(id);
            if (cursor.moveToFirst()){
                //using cursor to display each comedian's detail in database
                String description = String.format(" %s \n %s \n %s \n \n %s",cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_NAME)),
                "Nationality: "+ cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_NATIONALITY)),
                "Age: "+ cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_AGE)),
                ""+ cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_HISTORY)));

                int imageID = cursor.getInt(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_PICTURE));

                //setting text to comedian details
                TextView textView = (TextView)findViewById(R.id.detail_textview);
                textView.setText(description);

                //setting image to comedian details
                ImageView imageView = (ImageView)findViewById(R.id.detail_imageview);
                imageView.setImageResource(imageID);
            }
        }
    }
}
