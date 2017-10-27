package com.imagecolletorsllc.imagecollectors;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {

    //init textview variables
    TextView clientName;
    TextView clientEmail;
    TextView clientBackground;
    TextView clientPhone;

    TextView labelName;
    TextView labelEmail;
    TextView labelBackground;
    TextView labelPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }
    @Override
    protected void onStart(){
        super.onStart();
        resetTextViews();
        //get bundle data sent from the previous class
        Bundle b = getIntent().getExtras();
        //check if bundle is null
        if(b != null){
            //call the clientinfo function to update the textviews
            setClientInfo(b.getString("name"), b.getString("email"), b.getString("background"), b.getString("phone") == null ? "" : b.getString("phone"));
        }

    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    //reset the textview text
    public void resetTextViews(){
        //init the vars to the textviews
        clientName = (TextView) findViewById(R.id.clientNameText);
        clientEmail = (TextView) findViewById(R.id.clientEmailText);
        clientBackground = (TextView) findViewById(R.id.clientBackgroundText);
        clientPhone = (TextView) findViewById(R.id.clientPhonenumberText);
        //set the textviews to empty strings
        clientName.setText("");
        clientEmail.setText("");
        clientBackground.setText("");
        clientPhone.setText("");
    }
    public void setClientInfo(String name, String email, String background, String phone){
        //init the vars to the textviews
        clientName = (TextView) findViewById(R.id.clientNameText);
        clientEmail = (TextView) findViewById(R.id.clientEmailText);
        clientBackground = (TextView) findViewById(R.id.clientBackgroundText);
        clientPhone = (TextView) findViewById(R.id.clientPhonenumberText);
        //init the label vars to the labels
        labelName = (TextView) findViewById(R.id.labelName);
        labelEmail = (TextView) findViewById(R.id.labelEmail);
        labelBackground = (TextView) findViewById(R.id.labelBackground);
        labelPhone = (TextView) findViewById(R.id.labelPhone);
        //set the labels to bold
        labelName.setTypeface(null, Typeface.BOLD);
        labelEmail.setTypeface(null, Typeface.BOLD);
        labelBackground.setTypeface(null, Typeface.BOLD);
        labelPhone.setTypeface(null, Typeface.BOLD);
        //set the text of the textviews to the client info
        clientName.setText(name);
        clientEmail.setText(email);
        clientBackground.setText(background);
        clientPhone.setText(phone);
        //set the text to textsize 25
        clientName.setTextSize(25);
        clientEmail.setTextSize(25);
        clientBackground.setTextSize(25);
        clientPhone.setTextSize(25);
    }

}
