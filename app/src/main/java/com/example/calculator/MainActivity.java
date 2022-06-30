package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mariuszgromada.math.*;
import org.mariuszgromada.math.mxparser.Expression;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(v -> {
            if (getString(R.string.display).equals(display.getText().toString())) {
                display.setText("");
            }
        });

    }


    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos+1);

    }

    public void zeroBTN(View view) {
        updateText( getResources().getString(R.string.zero));
    }

    public void oneBTN(View view) {
        updateText( getResources().getString(R.string.one));
    }

    public void twoBTN(View view) {
        updateText( getResources().getString(R.string.two));
    }

    public void threeBTN(View view) {
        updateText( getResources().getString(R.string.three));
    }

    public void fourBTN(View view) {
        updateText( getResources().getString(R.string.four));
    }

    public void fiveBTN(View view) {
        updateText( getResources().getString(R.string.five));
    }

    public void sixBTN(View view) {
        updateText( getResources().getString(R.string.six));
    }

    public void sevenBTN(View view) {
        updateText( getResources().getString(R.string.seven));
    }

    public void eightBTN(View view) {
        updateText( getResources().getString(R.string.eight));
    }

    public void nineBTN(View view) {
        updateText( getResources().getString(R.string.nine));
    }


    public void subtractBTN(View view) {
        updateText( getResources().getString(R.string.subtract));
    }

    public void addBTN(View view) {
        updateText( getResources().getString(R.string.add));
    }

    public void divideBTN(View view) {
        updateText( getResources().getString(R.string.divide));
    }

    public void multiplyBTN(View view) {
        updateText( getResources().getString(R.string.multiply));
    }

    public void dZeroBTN(View view) {
        updateText( getResources().getString(R.string.dZero));
        int cursorPos = display.getSelectionStart();
        display.setSelection(cursorPos+1);
    }

    public void exponentBTN(View view) {
        updateText( getResources().getString(R.string.exponent));
    }

    public void pointBTN(View view) {
        updateText( getResources().getString(R.string.point));
    }

    public void clearBTN(View view) {
        display.setText("");
    }



    public void parenthesesBTN(View view) {
         int cursorPos = display.getSelectionStart();
         int openPar = 0;
         int closedPar = 0;
         int textLen = display.getText().length();
         for(int i=0;i<cursorPos;i++){
             if(display.getText().toString().substring(i,i+1).equals("(")){
                 openPar +=1;
             }
             else if(display.getText().toString().substring(i,i+1).equals(")")){
                 closedPar +=1;
             }
         }

         if(openPar == closedPar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
             updateText(getResources().getString(R.string.leftPar));
         }
         else if(openPar > closedPar && !display.getText().toString().substring(textLen-1,textLen).equals(")")){
             updateText(getResources().getString(R.string.rightPar));
         }
        display.setSelection(cursorPos + 1);

    }

    public void equalBTN(View view) {
       String userExp = display.getText().toString();

       userExp = userExp.replaceAll("÷","/");
       userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view) {
          int cursorPos = display.getSelectionStart();
          int textLen = display.getText().length();

          if(cursorPos!=0 && textLen!=0){
              SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
              selection.replace(cursorPos-1,cursorPos, "");
              display.setText(selection);
              display.setSelection(cursorPos-1);
          }
    }
}


