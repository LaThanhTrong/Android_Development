package com.labs.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton rb1 = findViewById(R.id.radioButton1);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        RadioGroup rg = findViewById(R.id.radioGroup);

        CheckBox cb1 = findViewById(R.id.checkBox1);
        CheckBox cb2 = findViewById(R.id.checkBox2);
        CheckBox cb3 = findViewById(R.id.checkBox3);
        CheckBox cb4 = findViewById(R.id.checkBox4);

        CheckBox[] cbGroup = {cb1, cb2, cb3, cb4};

        ImageButton btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "Mình là La Thanh Trọng ";
                Toast toast;

                for(int i = 0; i<cbGroup.length; i++) {
                    if(cbGroup[i].isChecked()) {
                        s = s + "- " + cbGroup[i].getText() + " ";
                    }
                }

                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);
                try {
                    s = s + "- " + rb.getText();
                } catch (Exception e) {
                    toast = Toast.makeText(MainActivity.this, "Please choose your gender.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                toast = Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}