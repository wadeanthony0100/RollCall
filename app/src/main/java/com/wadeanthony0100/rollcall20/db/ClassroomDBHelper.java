package com.wadeanthony0100.rollcall20.db;

/**
 * Created by Wade on 1/20/2015.
 */

import android.content.Context; //class used to reference an activity
import android.database.sqlite.SQLiteDatabase; //Android's SQLite database API
import android.database.sqlite.SQLiteOpenHelper; //Android's template for DBMS classes
import android.util.Log; //shortcut to the android logcat
import java.lang.Override;import java.lang.String;

public class ClassroomDBHelper extends SQLiteOpenHelper{
    private static final String LOGTAG = "DBHELPER";

    private static final String DATABASE_NAME = "data.com.wadeanthony0100.rollcall20.db"; //Set DB Name
    private static final int DATABASE_VERSION = 2; //Set DB Version

    //Make Strings for Tables and subsequent columns
    public static final String TABLE_STUDENT = "student";
    public static final String COLUMN_ID = "student_id";
    public static final String COLUMN_FIRSTNAME = "first_name";
    public static final String COLUMN_LASTNAME = "last_name";
    public static final String COLUMN_GPA = "gpa";

    //Make a CREATE TABLE SQL String
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_STUDENT + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_FIRSTNAME + " TEXT, " +
                    COLUMN_LASTNAME + " TEXT, " +
                    COLUMN_GPA + " REAL " +
                    ")";

    //Constructor Method
    public ClassroomDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);    //Pass the table creation string to the execute SQL method
        Log.i(LOGTAG, "Table has been created"); //Log that the DB is made
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

}
