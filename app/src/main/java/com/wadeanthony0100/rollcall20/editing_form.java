package com.wadeanthony0100.rollcall20;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wadeanthony0100.rollcall20.db.ClassroomDataSource;
import com.wadeanthony0100.rollcall20.model.Student;


public class editing_form extends ActionBarActivity {

    private static final String LOGTAG = "WADEERRORS";
    ClassroomDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dataSource = new ClassroomDataSource(this);
        dataSource.open();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editing_form, menu);
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
        if (id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickOK(View v){

        boolean isValid = true;
        String errorMessage = "";

        EditText firstField = (EditText) findViewById(R.id.firstnamefield);
        EditText lastField = (EditText) findViewById(R.id.lastnamefield);
        EditText idField = (EditText) findViewById(R.id.idnumfield);
        EditText gpaField = (EditText) findViewById(R.id.gpafield);

        String newFirstName = firstField.getText().toString();
        String newLastName = lastField.getText().toString();
        long newID = 0;
        float newGPA = 0.0f;
        try {
            newID = Long.parseLong(idField.getText().toString());
        }catch (NumberFormatException e){
            errorMessage += "Invalid ID Number.\n";
        }
        try {
            newGPA = Float.parseFloat(gpaField.getText().toString());
        }catch (NumberFormatException e) {
            errorMessage += "Invalid GPA Number.\n";
        }

        if ( newFirstName.equals("") || newLastName.equals("") || !errorMessage.equals("")){
            Log.i(LOGTAG, "Error Checking initiated...");
            isValid = false;
            TextView errorLabel = (TextView) findViewById(R.id.entry_error_label);

            if (newLastName.equals("")){
                errorMessage = "Must enter last name.\n" + errorMessage;
            }
            if (newFirstName.equals("")){
                errorMessage = "Must enter first name.\n" + errorMessage;
            }
            errorLabel.setText(errorMessage);
            errorLabel.setVisibility(View.VISIBLE);
        }

        if (isValid){
            dataSource.open();
            Student newStudent = new Student();
            newStudent.setFirstName(newFirstName);
            newStudent.setLastName(newLastName);
            newStudent.setGPA(newGPA);
            newStudent.setStudentId(newID);
            dataSource.create(newStudent);

            firstField.setText("");
            lastField.setText("");
            idField.setText("");
            gpaField.setText("");

            this.finish();
        }
    }

    public void onClickCANCEL(View v){
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }
}
