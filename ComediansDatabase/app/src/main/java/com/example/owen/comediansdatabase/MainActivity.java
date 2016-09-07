package com.example.owen.comediansdatabase;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CursorAdapter cursorAdapter;
    private SUCDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbhelper = SUCDatabaseHelper.getInstance(MainActivity.this);

        Cursor cursor = dbhelper.getComediansList();

        CursorAdapter cursorAdapter = new CursorAdapter(MainActivity.this,cursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView textView = (TextView)view.findViewById(R.id.name_textview);
                TextView yearTextView = (TextView)view.findViewById(R.id.year_textview);

                textView.setText(cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_COLUMN_NAME)));
                yearTextView.setText(cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_COLUMN_YEAR)));
            }
        };

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(cursorAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
