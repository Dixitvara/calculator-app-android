package com.example.calculatorapp;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView smallDisplay, bigDisplay;
    String bigNumber, smallNumber;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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
        vibrator.vibrate(25);
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
            case "÷":
            case "/":
            case "%":
            case "×":
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
        try {
            if (bigNumber.isEmpty()) return;
            if (bigNumber.charAt(0) == '-') {
                bigNumber = bigNumber.substring(1);
                updateDisplay();
                return;
            }
            bigNumber = "-" + bigNumber;
            updateDisplay();
        } catch (StringIndexOutOfBoundsException ignored) {}
    }

    private void appendNumber(String number) {
        if (number.equals(".") && !bigNumber.contains(".")) {
            bigNumber = bigNumber + number;
        }
        if (!number.equals(".") && !bigNumber.contains(".")) {
            bigNumber = bigNumber + number;
        }
        if (!number.equals(".") && bigNumber.contains(".")) {
            bigNumber = bigNumber + number;
        }
        updateDisplay();
    }

    private void chooseOperation(String operation) {
        if(smallNumber.isEmpty() && bigNumber.isEmpty()) return;
        if (!smallNumber.isEmpty() && bigDisplay.getText().toString().isEmpty()) {
            smallNumber = smallNumber.substring(0, smallNumber.length() - 1) + operation;
            updateDisplay();
            return;
        }
        if (!smallNumber.isEmpty()) {
            compute();
        }
        smallNumber = bigDisplay.getText().toString() + operation;
        bigNumber = "";
        updateDisplay();
    }

    private void compute() {
        if (bigDisplay.getText().toString().isEmpty()) {
            return;
        }
        String operator = smallNumber.substring(smallNumber.length() - 1);
        smallNumber = smallNumber.substring(0, smallNumber.length() - 1);
        double computation;
        double previousNumber = parseDouble(smallNumber);
        double currentNumber = parseDouble(bigNumber);

        switch (operator) {
            case "+":
                computation = previousNumber + currentNumber;
                break;
            case "-":
                computation = previousNumber - currentNumber;
                break;
            case "×":
                computation = previousNumber * currentNumber;
                break;
            case "÷":
                computation = previousNumber / currentNumber;
                break;
            case "%":
                computation = previousNumber % currentNumber;
                break;
            default:
                return;
        }
        bigNumber = String.valueOf(computation);
        smallNumber = "";
        updateDisplay();
    }

    private void updateDisplay() {
        bigDisplay.setText(bigNumber);
        smallDisplay.setText(smallNumber);
    }

    private void clearAll() {
        smallNumber = "";
        bigNumber = "";
        vibrator.vibrate(100);
        updateDisplay();
    }

    private void delete() {
        try {
            if (bigDisplay.getText().toString().isEmpty()) {
                bigNumber = smallNumber.substring(0, smallNumber.length() - 1);
                smallNumber = "";
                updateDisplay();
                return;
            }
            bigNumber = bigNumber.substring(0, bigNumber.length() - 1);
            updateDisplay();
        } catch (StringIndexOutOfBoundsException ignored) {
        }
    }
}