package com.example.lab11;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveData extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");
        name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phone);
        address = findViewById(R.id.Address);
        getdata();
    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(RetrieveData.this, "No data found.", Toast.LENGTH_SHORT).show();
                    return;
                }

                EmployeeInfo employeeInfo = snapshot.getValue(EmployeeInfo.class);
                if (employeeInfo != null) {
                    name.setText("Name: " + employeeInfo.getEmployeeName());
                    phone.setText("Phone: " + employeeInfo.getEmployeeContactNumber());
                    address.setText("Address: " + employeeInfo.getEmployeeAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RetrieveData.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
