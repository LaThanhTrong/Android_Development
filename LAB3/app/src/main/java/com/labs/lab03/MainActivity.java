package com.labs.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txta = findViewById(R.id.txta);
        EditText txtb = findViewById(R.id.txtb);
        TextView res = findViewById(R.id.result);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button mul = findViewById(R.id.mul);
        Button div = findViewById(R.id.div);
        Button and = findViewById(R.id.and);


        plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String a = txta.getText().toString();
                String b = txtb.getText().toString();
                if(TextUtils.isEmpty(a)) {
                    txta.setError("a is empty!");
                    return;
                }
                else if(TextUtils.isEmpty(b)) {
                    txtb.setError("b is empty!");
                    return;
                }
                else{
                    try {
                        double numA = Double.parseDouble(a);
                        double numB = Double.parseDouble(b);
                        double result = numA + numB;

                        if (result == (int) result) {
                            res.setText(String.valueOf((int) result));
                        } else {
                            res.setText(String.valueOf(result));
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        res.setText("Error: Invalid input");
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String a = txta.getText().toString();
                String b = txtb.getText().toString();
                if(TextUtils.isEmpty(a)) {
                    txta.setError("a is empty!");
                    return;
                }
                else if(TextUtils.isEmpty(b)) {
                    txtb.setError("b is empty!");
                    return;
                }
                else{
                    try {
                        double numA = Double.parseDouble(a);
                        double numB = Double.parseDouble(b);
                        double result = numA - numB;

                        if (result == (int) result) {
                            res.setText(String.valueOf((int) result));
                        } else {
                            res.setText(String.valueOf(result));
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        res.setText("Error: Invalid input");
                    }
                }
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String a = txta.getText().toString();
                String b = txtb.getText().toString();
                if(TextUtils.isEmpty(a)) {
                    txta.setError("a is empty!");
                    return;
                }
                else if(TextUtils.isEmpty(b)) {
                    txtb.setError("b is empty!");
                    return;
                }
                else{
                    try {
                        double numA = Double.parseDouble(a);
                        double numB = Double.parseDouble(b);
                        double result = numA * numB;

                        if (result == (int) result) {
                            res.setText(String.valueOf((int) result));
                        } else {
                            res.setText(String.valueOf(result));
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        res.setText("Error: Invalid input");
                    }
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String a = txta.getText().toString();
                String b = txtb.getText().toString();

                if (TextUtils.isEmpty(a)) {
                    txta.setError("a is empty!");
                    return;
                } else if (TextUtils.isEmpty(b)) {
                    txtb.setError("b is empty!");
                    return;
                } else {
                    try {
                        double numA = Double.parseDouble(a);
                        double numB = Double.parseDouble(b);

                        if (numB == 0) {
                            res.setText("Error: Division by zero");
                        } else {
                            double result = numA / numB;
                            if (result == (int) result) {
                                res.setText(String.valueOf((int) result));
                            } else {
                                res.setText(String.valueOf(result));
                            }
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        res.setText("Error: Invalid input");
                    }
                }
            }
        });

        and.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String a = txta.getText().toString();
                String b = txtb.getText().toString();

                if (TextUtils.isEmpty(a)) {
                    txta.setError("a is empty!");
                    return;
                } else if (TextUtils.isEmpty(b)) {
                    txtb.setError("b is empty!");
                    return;
                } else {
                    try {
                        int numA = Double.valueOf(a).intValue();
                        int numB = Double.valueOf(b).intValue();
                        res.setText(String.valueOf(numA)+String.valueOf(numB));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        res.setText("Error: Invalid input");
                    }
                }
            }
        });
    }
}