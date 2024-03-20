package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private Button addNoteBtn, viewNoteBtn;
    private EditText titleEdt, descEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView) findViewById(R.id.txtDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        addNoteBtn = findViewById(R.id.save);
        viewNoteBtn = findViewById(R.id.cancel);
        titleEdt = findViewById(R.id.editTextText);
        descEdt = findViewById(R.id.editTextTextMultiLine);
        showDate(year, month+1, day);
        DBHandler dbHandler = new DBHandler(MainActivity.this);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdt.getText().toString();
                String desc = descEdt.getText().toString();
                String date = dateView.getText().toString();
                if (title.isEmpty() && desc.isEmpty() && date.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill the note!", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewNote(title, desc, date);
                Toast.makeText(MainActivity.this, "Note has been added.", Toast.LENGTH_SHORT).show();
                titleEdt.setText("");
                descEdt.setText("");
            }
        });

        viewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewNotes.class);
                startActivity(i);
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
        DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0,
                                  int arg1, int arg2, int arg3) {

                showDate(arg1, arg2+1, arg3);
            }
        };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    private void showDate(int year, int month, int day) {
        String currentTime = getCurrentDateTime().substring(11);
        String formattedDate = new StringBuilder()
                .append(day).append("/")
                .append(month).append("/")
                .append(year).append(" ")
                .append(currentTime)
                .toString();

        dateView.setText(formattedDate);
    }
}