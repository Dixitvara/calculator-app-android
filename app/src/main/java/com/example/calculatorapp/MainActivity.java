package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView smallDisplay, bigDisplay;
    String bigNumber, smallNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallDisplay = findViewById(R.id.smallDisplay);
        bigDisplay = findViewById(R.id.bigDisplay);

        bigNumber = "";
        smallNumber = "";

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

    private void assignId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();

        switch (btnText) {
            case "AC":
                clearAll();
                return;
            case "Del":
                delete();
                return;
            case "+":
            case "/":
            case "%":
            case "*":
            case "-":
                chooseOperation(btnText);
                return;
            case "=":
                compute();
                return;
            case "+/-":
                negativePositive();
                return;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
                appendNumber(btnText);
        }
    }

    private void negativePositive() {

    }

    private void appendNumber(String number) {
        if (number.equals(".") && !bigNumber.contains(".")) {
            bigNumber = bigNumber + number;
            updateDisplay();
        }
        if (!number.equals(".") && !bigNumber.contains(".")) {
            bigNumber = bigNumber + number;
            updateDisplay();
        }
        if (!number.equals(".") && bigNumber.contains(".")){
            bigNumber = bigNumber + number;
            updateDisplay();
        }
    }

    private void chooseOperation(String operation) {

    }

    private void compute() {

    }

    private void updateDisplay() {
        bigDisplay.setText(bigNumber);
        smallDisplay.setText(smallNumber);
    }

    private void clearAll() {
        smallNumber = "";
        bigNumber = "";
        updateDisplay();
    }

    private void delete() {
//        bigNumber.charAt(bigNumber.length());
    }
}