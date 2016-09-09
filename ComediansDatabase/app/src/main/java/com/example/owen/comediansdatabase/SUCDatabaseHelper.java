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

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Comedians_db";
    public static final String COMEDIANS_TABLE_NAME = "Comedians";
    public static final String COMEDIANS_COLUMN_ID = "_id";
    public static final String COMEDIANS_NAME = "name";
    public static final String COMEDIANS_AGE = "age";
    public static final String COMEDIANS_NATIONALITY = "nationality";
    public static final String COMEDIANS_HISTORY = "history";
    public static final String COMEDIANS_PICTURE = "picture";
    public static final String [] COMEDIANS_COLUMNS = {COMEDIANS_COLUMN_ID, COMEDIANS_NAME, COMEDIANS_AGE, COMEDIANS_NATIONALITY,COMEDIANS_HISTORY, COMEDIANS_PICTURE};

    public static final String SQL_CREATE_COMEDIANS_TABLE =
            "CREATE TABLE " + COMEDIANS_TABLE_NAME +
                    "( " +
                    COMEDIANS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COMEDIANS_NAME + " TEXT," +
                    COMEDIANS_NATIONALITY + " TEXT," +
                    COMEDIANS_AGE + " TEXT," +
                    COMEDIANS_HISTORY + " TEXT," +
                    COMEDIANS_PICTURE + " TEXT )";
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
        values.put(COMEDIANS_NATIONALITY, "American");
        values.put(COMEDIANS_AGE, "47");
        values.put(COMEDIANS_HISTORY, R.string.patton);
        values.put(COMEDIANS_PICTURE, R.drawable.pattonoswalt);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Louis CK");
        values.put(COMEDIANS_NATIONALITY, "American");
        values.put(COMEDIANS_AGE, "48");
        values.put(COMEDIANS_PICTURE, R.drawable.louisck);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Hannibal Buress");
        values.put(COMEDIANS_NATIONALITY, "American");
        values.put(COMEDIANS_AGE, "33");
        values.put(COMEDIANS_PICTURE, R.drawable.hannibalburess);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Jimmy Carr");
        values.put(COMEDIANS_NATIONALITY, "British");
        values.put(COMEDIANS_AGE, "43");
        values.put(COMEDIANS_PICTURE, R.drawable.jimmycarr);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "John Oliver");
        values.put(COMEDIANS_NATIONALITY, "British");
        values.put(COMEDIANS_AGE, "39");
        values.put(COMEDIANS_PICTURE, R.drawable.johnoliver);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Jim Jefferies");
        values.put(COMEDIANS_NATIONALITY, "Australian");
        values.put(COMEDIANS_AGE, "39");
        values.put(COMEDIANS_PICTURE, R.drawable.jimjefferies);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(COMEDIANS_NAME, "Jim Carrey");
        values.put(COMEDIANS_NATIONALITY, "Canadian");
        values.put(COMEDIANS_AGE, "54");
        values.put(COMEDIANS_PICTURE, R.drawable.jimcarrey);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);
    }

    public void addComedian(String name, String year){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMEDIANS_NAME, name);
        values.put(COMEDIANS_AGE, year);
        sqLiteDatabase.insert(COMEDIANS_TABLE_NAME, null, values);     //creates a new row into our table
    }

    public Cursor getComediansList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                null,
                null,
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(cursor);
        return cursor;
    }

    public Cursor searchComedians(String query){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor scursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                COMEDIANS_NAME + " LIKE ? OR " + COMEDIANS_NATIONALITY + " LIKE ? OR " + COMEDIANS_AGE + " LIKE ? ",
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(scursor);
        return scursor;
    }

    public Cursor detailsComedian(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor dcursor = sqLiteDatabase.query(COMEDIANS_TABLE_NAME,
                COMEDIANS_COLUMNS,
                COMEDIANS_COLUMN_ID + "= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(dcursor);
        return dcursor;
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
