package com.imagecolletorsllc.imagecollectors;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionScreen extends AppCompatActivity {
    //init the button vars
    private Button addClientButton;
    private Button viewClientButton;
    private Button setupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Snackbar.make(this.findViewById(android.R.id.content),
                        "Please Grant Permissions to upload profile photo",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(SelectionScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(SelectionScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            setupButtons();
        }

    }


    @Override
    protected void onStart(){
        super.onStart();
    }
    //change screen function
    private void goToAddScreen(){
        //set the intent to the mainscreen
        Bundle b = new Bundle();
        Intent intent = new Intent(this, pinentry.class);
        b.putInt("screen", 1);
        intent.putExtras(b);
        //start the main screen activity
        startActivity(intent);
    }
    private void setupButtons(){
        setupButton = (Button) findViewById(R.id.setupButton);
        viewClientButton = (Button) findViewById(R.id.viewClientButton);
        addClientButton = (Button) findViewById(R.id.addClientButton);
        //set listener for button click
        addClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the addclient screen on click
                goToAddScreen();
            }
        });
        //set listener for button click
        viewClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the viewclient screen on click
                goToViewScreen();
            }
        });
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoConventionSetup();
            }
        });
    }
    //change screen function
    private void goToViewScreen(){
        Bundle b = new Bundle();
        //set the intent to the mainscreen
        Intent intent = new Intent(this, pinentry.class);
        b.putInt("screen", 2);
        intent.putExtras(b);
        //start the main screen activity
        startActivity(intent);
    }
    private void gotoConventionSetup(){
        Bundle b = new Bundle();
        //set the intent to the mainscreen
        Intent intent = new Intent(this, pinentry.class);
        b.putInt("screen", 3);
        intent.putExtras(b);
        //start the main screen activity
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    boolean writeExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (writeExternalFile && readExternalFile) {
                        setupButtons();
                    } else {
                        Snackbar.make(this.findViewById(android.R.id.content),
                                "Please Grant Permissions to read and write external files.",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(SelectionScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                                    }
                                }).show();
                    }
                }
        }
    }
}
