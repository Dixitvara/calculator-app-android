package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView smallDisplay, bigDisplay;
    StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallDisplay = findViewById(R.id.smallDisplay);
        bigDisplay = findViewById(R.id.bigDisplay);
        builder = new StringBuilder();
        bigDisplay.setText("0");
        // operation Button
        assignId(R.id.allClear);
        assignId(R.id.clear);
        assignId(R.id.plusOrMinus);
        assignId(R.id.modulo);
        assignId(R.id.divide);
        assignId(R.id.multiply);
        assignId(R.id.subtraction);
        assignId(R.id.addition);
        assignId(R.id.equals);

        // Number Button
        assignId(R.id.btn_0);
        assignId(R.id.btn_1);
        assignId(R.id.btn_2);
        assignId(R.id.btn_3);
        assignId(R.id.btn_4);
        assignId(R.id.btn_5);
        assignId(R.id.btn_6);
        assignId(R.id.btn_7);
        assignId(R.id.btn_8);
        assignId(R.id.btn_9);
        assignId(R.id.btn_);

    }

    void assignId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();

        if (btnText.equals("AC")) {
            clearAll();
            return;
        }
        if (btnText.equals("C")) {
            clear();
            return;
        }

        // append text to display
        appendText(v);
    }

    // function that append text to display
    void appendText(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();

        builder.append(btnText);
        bigDisplay.setText(builder);
    }

    // method that clear the whole display
    void clearAll() {
        builder.delete(0, builder.length());
        bigDisplay.setText("0");
    }

    // method that delete a character
    void clear() {
        try {
            if (builder.length() != 0) {
                builder.deleteCharAt(builder.length() - 1);
                bigDisplay.setText(builder);
            }
            if (builder.length() <= 0) {
                bigDisplay.setText("0");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}