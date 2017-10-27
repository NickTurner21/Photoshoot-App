package com.imagecolletorsllc.imagecollectors;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        //get the submit button
        Button submitButton = (Button) findViewById(R.id.submitButton);
        //set the click listener of the submit button
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Utils.currentConvention == null){
                    Toast.makeText(EntryActivity.this, "Please setup the current convention first.", Toast.LENGTH_LONG).show();
                    return;
                }
                //init the edit text vars
                EditText clientNameText = (EditText) findViewById(R.id.nameText);
                EditText clientEmailText = (EditText) findViewById(R.id.emailText);
                EditText clientBackgroundText = (EditText) findViewById(R.id.backgroundText);
                EditText clientPhoneText = (EditText) findViewById(R.id.phoneText);
                //get the input client information
                String clientName = clientNameText.getText().toString();
                String clientEmail = clientEmailText.getText().toString();
                String clientBackground = clientBackgroundText.getText().toString();
                String clientPhone = clientPhoneText.getText().toString();
                //create client object
                Client client;
                //check if client has phone number
                if(clientPhone == ""){
                    //init client object
                    client = new Client(clientName, clientEmail, clientBackground);
                }else{
                    //init client object
                    client = new Client(clientName, clientEmail, clientBackground, clientPhone);
                }
                try {
                    //load json string to variable
                    String valueFromJson = Utils.loadJSONFromAsset(EntryActivity.this);
                    //initialize the id var
                    //long id = 0;
                    //check if json string is null
                    /***if(valueFromJson != null){
                        //create json object from json stirng
                        JSONObject photoshootsjsonobj = new JSONObject(valueFromJson);


                        //loop through the ids until the last one is set
                        for(Iterator<String> iter = photoshootsjsonobj.keys(); iter.hasNext();) {
                            try {
                                //set id to the current item in list
                                id = Integer.parseInt(iter.next());
                            }catch (NumberFormatException ex){
                                //print number errors
                                ex.printStackTrace();
                            }
                        }
                    }***/
                    //create json object to the current json
                    JSONObject clientToAddID;
                    if(valueFromJson == null){
                        clientToAddID = new JSONObject();
                    }else{
                        clientToAddID = new JSONObject(valueFromJson);
                    }

                    Convention cConv = Utils.currentConvention;
                    String conventionName = cConv.getConventionName();
                    int startingClient = cConv.getStartingClient();
                    int convYear = cConv.getConventionYear();
                    int convMonth = cConv.getConventionMonth();
                    int convday = cConv.getConventionDay();

                    //create new json object
                    JSONObject clientToAdd = new JSONObject();
                    //put client info in to the new json object
                    clientToAdd.put("conventionName", conventionName);
                    clientToAdd.put("conventionYear", convYear);
                    clientToAdd.put("conventionMonth", convMonth);
                    clientToAdd.put("conventionDay", convday);

                    clientToAdd.put("clientName", client.getClientName());
                    clientToAdd.put("clientEmail", client.getClientEmail());
                    clientToAdd.put("clientBackground", client.getClientBackground());
                    clientToAdd.put("clientPhoneNumber", client.getClientPhone());
                    //puts the photoshoot number one more than the last
                    clientToAddID.put(String.valueOf(startingClient + 1), clientToAdd);
                    try {
                        //gets photoshoot json file
                        File file = new File(Environment.getExternalStorageDirectory(), "PhotoShoots.json");
                        //check if file exists
                        if(!file.exists()){
                            //create file if it doesn't exist
                            file.createNewFile();
                        }
                        //create bufferwriter object from file
                        BufferedWriter output = new BufferedWriter(new FileWriter(file));
                        //write json to file
                        output.write(clientToAddID.toString());
                        //output.append(clientToAddID.toString());
                        //close the buffrewriter
                        output.close();
                    }catch(IOException exx){
                        //print io write errors
                        exx.printStackTrace();
                    }
                } catch(JSONException e){
                    //print json errors
                    e.printStackTrace();
                }
                //backToMainScreen();
                Toast.makeText(EntryActivity.this, "Client added", Toast.LENGTH_SHORT).show();
                //call client form function
                resetClientForm();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(Utils.currentConvention == null){
            return;
        }
        TextView convName = (TextView) findViewById(R.id.conventionDisplay);
        TextView convDate = (TextView) findViewById(R.id.dateDisplay);
        String year = String.valueOf(Utils.currentConvention.getConventionYear());
        String month = String.valueOf(Utils.currentConvention.getConventionMonth());
        String day = String.valueOf(Utils.currentConvention.getConventionDay());
        String name = Utils.currentConvention.getConventionName();
        convName.setText(name);
        convDate.setText(month + "/" + day + "/" + year);
    }
    //clientform reset function
    public void resetClientForm(){
        //grab edittext and set to the vars
        EditText clientNameText = (EditText) findViewById(R.id.nameText);
        EditText clientEmailText = (EditText) findViewById(R.id.emailText);
        EditText clientBackgroundText = (EditText) findViewById(R.id.backgroundText);
        EditText clientPhoneText = (EditText) findViewById(R.id.phoneText);
        //set edit texts to blank strings
        clientNameText.setText("");
        clientEmailText.setText("");
        clientBackgroundText.setText("");
        clientPhoneText.setText("");
        //set focus to the top edit text
        clientNameText.requestFocus();
        //grab input object for keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        //open keyboard
        imm.showSoftInput(clientNameText, InputMethodManager.SHOW_IMPLICIT);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, SelectionScreen.class);
        startActivity(intent);
    }


}
