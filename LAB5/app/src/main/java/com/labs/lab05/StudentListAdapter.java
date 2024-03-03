package com.labs.lab05;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {
    final ArrayList<Student> listStd;

    public StudentListAdapter(ArrayList<Student> listStd) {
        this.listStd = listStd;
    }

    @Override
    public int getCount() {
        return listStd.size();
    }

    @Override
    public Object getItem(int position) {
        return listStd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listStd.get(position).id.hashCode();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewStudents;
        if (convertView == null) {
            viewStudents = View.inflate(parent.getContext(), R.layout.custom_list, null);
        } else viewStudents = convertView;

        Student std = (Student) getItem(position);
        ((TextView) viewStudents.findViewById(R.id.id)).setText(String.format("IDs: %s", std.id));
        ((TextView) viewStudents.findViewById(R.id.name)).setText(String.format("Fullname : %s", std.name));
        ((TextView) viewStudents.findViewById(R.id.cls)).setText(String.format("Class %s", std.cls));
        ((TextView) viewStudents.findViewById(R.id.gender)).setText(String.format("Gender %s", std.gender));
        ((TextView) viewStudents.findViewById(R.id.listStatus)).setText(String.format("Status:\n%s", std.enStatus));
        return viewStudents;
    }
}
