package com.example.owen.comediansdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SUCDatabaseHelper helper = SUCDatabaseHelper.getInstance(this);
        int id = getIntent().getIntExtra("id",-1);

        if(id >= 0) {
            Cursor cursor = helper.detailsComedian(id);
            if (cursor.moveToFirst()){
                String description = String.format( cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_NAME)));
                TextView textView = (TextView)findViewById(R.id.detail_text);
                textView.setText(description);
            }

        }
    }
}
