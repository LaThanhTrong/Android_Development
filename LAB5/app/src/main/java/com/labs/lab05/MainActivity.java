package com.labs.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static StudentManager studentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        studentManager = StudentManager.getInstance();

        EditText ids = findViewById(R.id.ids);
        EditText name = findViewById(R.id.name);
        Spinner classes = findViewById(R.id.classes);
        RadioGroup gender = findViewById(R.id.gender);
        RadioButton male = findViewById(R.id.male);
        RadioButton female = findViewById(R.id.female);
        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 = findViewById(R.id.cb3);
        CheckBox[] cbGroup = {cb1, cb2, cb3};
        Button ok = findViewById(R.id.ok);
        Button cancel = findViewById(R.id.cancel);
        Button stdlistButton = findViewById(R.id.stdlistButton);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String s = "";
                String status = "";
                int isEmpty = 0;

                try{
                    if(cbGroup[cbGroup.length - 1].isChecked()) {
                        for (CheckBox checkBox : cbGroup) {
                            if (checkBox != cbGroup[cbGroup.length - 1]) {
                                checkBox.setChecked(false);
                            }
                        }
                    }

                    for(int i = 0; i < cbGroup.length; i++) {
                        if(!cbGroup[i].isChecked()) {
                            isEmpty += 1;
                        } else {
                            status = status + "- " + cbGroup[i].getText() + "\n";
                        }
                    }
                    status = status.trim();
                    if (status.endsWith("-")) {
                        status = status.substring(0, status.length() - 2);
                    }

                    if(name.getText() == null || ids.getText() == null || isEmpty == cbGroup.length) {
                        toast = Toast.makeText(MainActivity.this, "Please put in all fields!.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        int id = gender.getCheckedRadioButtonId();
                        RadioButton rb = findViewById(id);
                        studentManager.addStudent(new Student(ids.getText().toString(), classes.getSelectedItem().toString(), name.getText().toString(), rb.getText().toString(), status));
                        s = s + name.getText() + " (" + ids.getText() + "), " +rb.getText()+" as, " + classes.getSelectedItem().toString() +" "+ status;
                        toast = Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                catch(Exception e) {
                    toast = Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        stdlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        });

    }
}