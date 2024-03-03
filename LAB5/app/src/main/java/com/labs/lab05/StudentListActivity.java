package com.labs.lab05;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private static final String TAG = "StudentListActivity";
    public static StudentManager studentManager;
    StudentListAdapter studentListAdapter;
    ListView listViewStudent;

    Button back, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_list);
        Log.d(TAG, "onCreate: Starting.");
        studentManager = StudentManager.getInstance();
        ArrayList<Student> listStd = studentManager.getListStd();
        listViewStudent = findViewById(R.id.stdlist);
        listViewStudent.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        studentListAdapter = new StudentListAdapter(listStd);
        try {
            listViewStudent.setAdapter(studentListAdapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student student = (Student) studentListAdapter.getItem(position);
                    Toast.makeText(StudentListActivity.this, student.name, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            return;
        }

        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentListActivity.this, MainActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = listViewStudent.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    Object selectedItem = studentListAdapter.getItem(position);

                    if (selectedItem instanceof Student) {
                        final Student selectedStudent = (Student) selectedItem;

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentListActivity.this);
                        builder.setMessage("Are you sure you want to delete " + selectedStudent.getName() + "?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        studentManager.removeStudent(selectedStudent);
                                        studentListAdapter.notifyDataSetChanged();

                                        String studentName = selectedStudent.getName();
                                        Toast.makeText(StudentListActivity.this, "Student deleted: " + studentName, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // do nothing
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        // Handle the case where the selected item is not of type Student
                        Toast.makeText(StudentListActivity.this, "Invalid item type selected.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(StudentListActivity.this, "Please select a student to delete.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}