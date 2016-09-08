package com.example.owen.comediansdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        SUCDatabaseHelper helper = SUCDatabaseHelper.getInstance(this);
//        int id = getIntent().getIntExtra("id",-1);
//
//        if(id >= 0) {
//            String description = helper.getComediansList(id);
//            TextView textView = (TextView) findViewById(R.id.detail_text);
//            textView.setText(description);
//        }
    }
}
