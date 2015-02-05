package com.wadeanthony0100.rollcall20;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.wadeanthony0100.rollcall20.R;

/**
 * Created by Wade on 1/20/2015.
 */
public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

    protected TextView vfirstname;
    protected TextView vlastname;
    protected TextView vgpa;
    protected TextView vstudentid;

    public StudentViewHolder(View v) {
        super(v);
        vfirstname =  (TextView) v.findViewById(R.id.firstname);
        vlastname = (TextView)  v.findViewById(R.id.lastname);
        vgpa = (TextView)  v.findViewById(R.id.gpa);
        vstudentid = (TextView) v.findViewById(R.id.studentid);
        v.setOnCreateContextMenuListener(this);
        
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select an action for "+vfirstname.getText().toString()+" "+vlastname.getText().toString());
        menu.add(0, v.getId(), 0, "Delete Student");
    }

}
