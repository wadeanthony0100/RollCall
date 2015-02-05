package com.wadeanthony0100.rollcall20.db;

/**
 * Created by Wade on 1/20/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.wadeanthony0100.rollcall20.MainActivity;
import com.wadeanthony0100.rollcall20.model.Student;

import java.lang.String;import java.util.ArrayList;
import java.util.List;

public class ClassroomDataSource {

    public static final String LOGTAG="DBDATASOURCE";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            ClassroomDBHelper.COLUMN_ID,
            ClassroomDBHelper.COLUMN_FIRSTNAME,
            ClassroomDBHelper.COLUMN_LASTNAME,
            ClassroomDBHelper.COLUMN_GPA       };

    public ClassroomDataSource(Context context) {
        dbhelper = new ClassroomDBHelper(context);
    }

    public void open() {
        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        Log.i(LOGTAG, "Database closed");
        dbhelper.close();
    }

    public Student create(Student student){
        ContentValues values = new ContentValues();
        values.put(ClassroomDBHelper.COLUMN_FIRSTNAME, student.getFirstName());
        values.put(ClassroomDBHelper.COLUMN_LASTNAME, student.getLastName());
        values.put(ClassroomDBHelper.COLUMN_GPA, student.getGPA());
        values.put(ClassroomDBHelper.COLUMN_ID, student.getStudentId());
        database.insert(ClassroomDBHelper.TABLE_STUDENT, null, values);
        return student;
    }

    public void removeWhere(String SQLconditions){
        //String table, String whereClause, String[] whereArgs
        int deletedRows = database.delete(ClassroomDBHelper.TABLE_STUDENT, SQLconditions, new String[0]);
        Log.i(LOGTAG, "Deleted " + deletedRows + " rows");
    }

    public List<Student> findAll(){
        List<Student> studentList = new ArrayList<>();
        Cursor myResult = database.query(ClassroomDBHelper.TABLE_STUDENT, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "Returned " + myResult.getCount() + " rows.");
        if (myResult.getCount() > 0){

            while(myResult.moveToNext()){
                Student myNewStudent = new Student();

                //initialze the new Student() object with data from the com.wadeanthony0100.rollcall20.db
                myNewStudent.setStudentId(myResult.getLong(myResult.getColumnIndex(ClassroomDBHelper.COLUMN_ID)));
                myNewStudent.setFirstName(myResult.getString(myResult.getColumnIndex(ClassroomDBHelper.COLUMN_FIRSTNAME)));
                myNewStudent.setLastName(myResult.getString(myResult.getColumnIndex(ClassroomDBHelper.COLUMN_LASTNAME)));
                myNewStudent.setGPA(myResult.getFloat(myResult.getColumnIndex(ClassroomDBHelper.COLUMN_GPA)));

                studentList.add(myNewStudent);
            }
        }
        return studentList;
    }
}