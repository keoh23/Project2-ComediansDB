package com.example.owen.comediansdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owen on 9/6/2016.
 */
public class SCDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Comedians.db";
    public static final String COMEDIANS_TABLE_NAME = "games";
    public static final String COMEDIANS_COLUMN_ID = "_id";
    public static final String COMEDIANS_COLUMN_NAME = "name";
    public static final String COMEDIANS_COLUMN_YEAR = "year";
    public static final String SQL_CREATE_COMEDIANS_TABLE =
            "CREATE TABLE "+COMEDIANS_TABLE_NAME+" " +
                    "( "+COMEDIANS_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COMEDIANS_COLUMN_NAME+" TEXT, " +
                    COMEDIANS_COLUMN_YEAR+" TEXT)";
    public static final String SQL_DROP_COMEDIANS_TABLE = "DROP TABLE IF EXISTS "+COMEDIANS_TABLE_NAME;

    public SCDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_COMEDIANS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_COMEDIANS_TABLE);
        onCreate(sqLiteDatabase);

    }

    public int insert(String name, String year){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMEDIANS_COLUMN_NAME, name);
        values.put(COMEDIANS_COLUMN_YEAR, year);
        long id = db.insert(COMEDIANS_TABLE_NAME, null, values);     //creates a new row into our table
        return ((int)id);
    }

    public Comedians getComedian (int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = new String[]{COMEDIANS_COLUMN_ID,COMEDIANS_COLUMN_NAME,COMEDIANS_COLUMN_YEAR};
        String selection = COMEDIANS_COLUMN_ID + " = ?";
        String[] selectionArgs = new String[]{ String.valueOf(id) };
        Cursor cursor = db.query(COMEDIANS_TABLE_NAME, projection, selection, selectionArgs, null, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(COMEDIANS_COLUMN_NAME));
        String year = cursor.getString(cursor.getColumnIndex(COMEDIANS_COLUMN_YEAR));

        return new Comedians(id, name, year);

    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String selection = COMEDIANS_COLUMN_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        db.delete(COMEDIANS_TABLE_NAME, selection, selectionArgs);
    }


}
