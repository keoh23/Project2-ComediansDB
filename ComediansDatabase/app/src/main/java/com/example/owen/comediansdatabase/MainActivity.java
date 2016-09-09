package com.example.owen.comediansdatabase;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CursorAdapter cursorAdapter;
    private SUCDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView)findViewById(R.id.list);
        dbhelper = SUCDatabaseHelper.getInstance(MainActivity.this);
        Cursor cursor = dbhelper.getComediansList();
        cursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView textView = (TextView) view.findViewById(R.id.name_textview);
                TextView nationTextView = (TextView) view.findViewById(R.id.nation_textview);
                TextView yearTextView = (TextView) view.findViewById(R.id.year_textview);

                textView.setText(cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_NAME)));
                nationTextView.setText(cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_NATIONALITY)));
                yearTextView.setText(cursor.getString(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_AGE)));
            }
        };
            listView.setAdapter(cursorAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    Cursor cursor = cursorAdapter.getCursor();
                    cursor.moveToPosition(i);
                    intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(SUCDatabaseHelper.COMEDIANS_COLUMN_ID)));
                    startActivity(intent);
                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            });
        handleIntent(getIntent());
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            Cursor cursor = dbhelper.searchComedians(query);
            DatabaseUtils.dumpCursor(cursor);
            cursor.moveToFirst();
            cursorAdapter.changeCursor(cursor);
            cursorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        FragmentManager fm = getFragmentManager();
        if (id == R.id.action_settings) {
            return true;
//        switch (item.getItemId()) {
//
//            case R.id.actionitem_alertdialog:
//                MyAlertDialogFragment alertDialog = new MyAlertDialogFragment();
//                alertDialog.setRetainInstance(true);
//                alertDialog.show(fm, "alertfragment");
//                break;
//            case R.id.actionitem_userdialog:
//               MyDialogFragment editNameDialog = new MyDialogFragment();
//                editNameDialog.setRetainInstance(true);
//                editNameDialog.show(fm, "userfragemnt");
//                break;
//
//            default:
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
