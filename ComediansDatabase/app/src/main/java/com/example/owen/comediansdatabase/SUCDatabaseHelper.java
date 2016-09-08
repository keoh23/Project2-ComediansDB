package com.example.owen.comediansdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owen on 9/6/2016.
 */
public class SUCDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Comedians_db";
    public static final String COMEDIANS_TABLE_NAME = "Comedians";
    public static final String COMEDIANS_COLUMN_ID = "_id";
    public static final String COMEDIANS_NAME = "name";
    public static final String COMEDIANS_YEAR = "year";
    public static final String [] COMEDIANS_COLUMNS = {COMEDIANS_COLUMN_ID, COMEDIANS_NAME, COMEDIANS_YEAR};

    public static final String SQL_CREATE_COMEDIANS_TABLE =
            "CREATE TABLE " + COMEDIANS_TABLE_NAME +
                    "( " +
                    COMEDIANS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COMEDIANS_NAME + " TEXT," +
                    COMEDIANS_YEAR + " TEXT )";
    public static final String SQL_DROP_COMEDIANS_TABLE = "DROP TABLE IF EXISTS "+COMEDIANS_TABLE_NAME;

    //Creating as Singleton class
    private static SUCDatabaseHelper instance;

    public static SUCDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance =  new SUCDatabaseHelper(context);
        }
        return instance;
    }

    public SUCDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_COMEDIANS_TABLE);
        addDefault(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DROP_COMEDIANS_TABLE);
        onCreate(sqLiteDatabase);

    }

    public void addDefault(SQLiteDatabase sqLiteDatabase){
        ContentValues values = new ContentValues();
        values.put(COMEDIANS_NAME, "Patton Oswalt");
        values.put(COMEDIANS_YEAR, "2010");
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Louis CK");
        values.put(COMEDIANS_YEAR, "2007");
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Hannibal Buress");
        values.put(COMEDIANS_YEAR, "2014");
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);
    }

//    public void addComedian(String name, String year){
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COMEDIANS_NAME, name);
//        values.put(COMEDIANS_YEAR, year);
//        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);     //creates a new row into our table
//    }

    public Cursor getComediansList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor searchComedians(String query){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                COMEDIANS_NAME + " LIKE ? OR " + COMEDIANS_YEAR + " LIKE ? ",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null,
                null,
                null);
        return cursor;
    }

    public Cursor detailsComedian(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                COMEDIANS_COLUMN_ID + "= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(cursor);
        return cursor;
    }


//    public Comedians getComedian (int id) {
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        String[] projection = new String[]{COMEDIANS_COLUMN_ID,COMEDIANS_NAME,COMEDIANS_YEAR};
//        String selection = COMEDIANS_COLUMN_ID + " = ?";
//        String[] selectionArgs = new String[]{ String.valueOf(id) };
//        Cursor cursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME, projection, selection, selectionArgs, null, null, null, null);
//        cursor.moveToFirst();
//        String name = cursor.getString(cursor.getColumnIndex(COMEDIANS_NAME));
//        String year = cursor.getString(cursor.getColumnIndex(COMEDIANS_YEAR));
//
//        return new Comedians(id, name, year);
//
//    }

    public void delete(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String selection = COMEDIANS_COLUMN_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        sqLiteDatabase.delete(COMEDIANS_TABLE_NAME, selection, selectionArgs);
    }


}
