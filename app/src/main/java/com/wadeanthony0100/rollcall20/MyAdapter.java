package com.wadeanthony0100.rollcall20;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wadeanthony0100.rollcall20.model.Student;

import java.util.List;


/**
 * Created by Wade on 1/20/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private List<Student> studentsList;

    public MyAdapter(List<Student> studentsList){
        this.studentsList = studentsList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student sl = studentsList.get(position);
        holder.vfirstname.setText(sl.getFirstName());
        holder.vlastname.setText(sl.getLastName());
        holder.vgpa.setText(Float.toString(sl.getGPA()));
        holder.vstudentid.setText(Long.toString(sl.getStudentId()));

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
