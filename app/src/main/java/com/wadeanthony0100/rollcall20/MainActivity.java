package com.wadeanthony0100.rollcall20;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wadeanthony0100.rollcall20.db.ClassroomDataSource;
import com.wadeanthony0100.rollcall20.model.Student;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static final String LOGTAG = "WADE_MAIN_ACTIVITY";

    private ClassroomDataSource dataSource;
    private ArrayList<Student> classroomList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager glm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(mRecyclerView);
        dataSource = new ClassroomDataSource(this);
        dataSource.open();
        //addData();

        populateCards();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_new_student){
            Intent intent = new Intent(this, editing_form.class);
            startActivity(intent);
            return true;
        }if (id == R.id.action_drop_table) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.long_click_card_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterViewCompat.AdapterContextMenuInfo info = (AdapterViewCompat.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.floating_delete_item){
            Log.i(LOGTAG, String.valueOf(info.id));
            populateCards();
            return true;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this, "Entered Landscape", Toast.LENGTH_LONG).show();
            glm.setSpanCount(3);
        }else{
            Toast.makeText(this, "Entered Portrait", Toast.LENGTH_LONG).show();
            glm.setSpanCount(2);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
        populateCards();
    }


    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }


    private void populateCards() {
        dataSource.open();
        classroomList = (ArrayList<Student>) dataSource.findAll();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        /*
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Student studentClicked = classroomList.get(position);
                        Log.i("WADE", "Clicked View at position " + studentClicked.getFirstName() + " " + studentClicked.getLastName());
                    }
                })
        );*/
        glm = new GridLayoutManager(this, 3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(glm);
        // specify an adapter
        mAdapter = new MyAdapter(classroomList);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void addData(){
        Student student1 = new Student();
        student1.setFirstName("Gene");
        student1.setLastName("Belcher");
        student1.setGPA(2.5f);
        student1.setStudentId(2014007);
        dataSource.create(student1);

        student1.setFirstName("Hannah");
        student1.setLastName("Hashbrown");
        student1.setGPA(2.6f);
        student1.setStudentId(2014008);
        dataSource.create(student1);

        student1.setFirstName("Ian");
        student1.setLastName("Inferred");
        student1.setGPA(2.7f);
        student1.setStudentId(2014009);
        dataSource.create(student1);

        student1.setFirstName("Jackie");
        student1.setLastName("Jam");
        student1.setGPA(2.8f);
        student1.setStudentId(2014010);
        dataSource.create(student1);

        student1.setFirstName("Kris");
        student1.setLastName("Kringle");
        student1.setGPA(2.9f);
        student1.setStudentId(2014011);
        dataSource.create(student1);

        student1.setFirstName("Larry");
        student1.setLastName("Lobster");
        student1.setGPA(3.0f);
        student1.setStudentId(2014012);
        dataSource.create(student1);
    }

}
