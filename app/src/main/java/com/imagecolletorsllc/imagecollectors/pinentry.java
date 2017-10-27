package com.imagecolletorsllc.imagecollectors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pinentry extends AppCompatActivity {

    private static TextView pinText;
    private static Button backButton;
    private static Button doneButton;
    private static Button button0;
    private static Button button1;
    private static Button button2;
    private static Button button3;
    private static Button button4;
    private static Button button5;
    private static Button button6;
    private static Button button7;
    private static Button button8;
    private static Button button9;
    private static int screenTogo;
    private static HashMap<Button, Integer> buttonList = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinentry);
        backButton = (Button) findViewById(R.id.backButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        pinText = (TextView) findViewById(R.id.pinText);
        pinText.setText("");
        buttonList.put(button0, 0);
        buttonList.put(button1, 1);
        buttonList.put(button2, 2);
        buttonList.put(button3, 3);
        buttonList.put(button4, 4);
        buttonList.put(button5, 5);
        buttonList.put(button6, 6);
        buttonList.put(button7, 7);
        buttonList.put(button8, 8);
        buttonList.put(button9, 9);
        for (final Map.Entry<Button, Integer> entry : buttonList.entrySet()) {
            entry.getKey().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        pinText.setText(pinText.getText().toString() + String.valueOf(entry.getValue()));
                    }catch(Exception e){
                        pinText.setText("");
                    }
                }
            });
        }
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("abc", pinText.getText().toString());
                if(pinText.getText().toString().equals("0000")){
                    switch(screenTogo){
                        case 1:
                            goToAddClientScreen();
                            return;
                        case 2:
                            goToClientViewScreen();
                            return;
                        case 3:
                            goToConventionSetup();
                            return;
                    }
                }else{
                    Toast.makeText(pinentry.this, "Incorrect Passcode", Toast.LENGTH_LONG).show();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pinText.setText(pinText.getText().toString().substring(0, pinText.getText().toString().length() - 1));
                }catch(Exception e){
                    pinText.setText("");
                }
            }
        });
    }
    @Override
    protected void onStart(){
        /*Bundle b = getIntent().getExtras();
        String screenToGo = null;
        if(b != null) {
            screenToGo = b.getString("screen");
        }
        final String goToScreen = screenToGo;*/
        super.onStart();
        Bundle b = getIntent().getExtras();
        if(b != null){
            Log.e("screen", String.valueOf(b.getInt("screen")));
            screenTogo = b.getInt("screen");
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        pinText.setText("");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void goToConventionSetup(){
        Intent intent = new Intent(this, ConventionSetupActivity.class);
        startActivity(intent);
    }

    private void goToAddClientScreen(){
        //set the intent to the mainscreen
        Intent intent = new Intent(this, EntryActivity.class);
        //start the main screen activity
        startActivity(intent);
    }
    private void goToClientViewScreen(){
        Intent intent = new Intent(this, MainScreen.class);
        //start the main screen activity
        startActivity(intent);
    }
}
