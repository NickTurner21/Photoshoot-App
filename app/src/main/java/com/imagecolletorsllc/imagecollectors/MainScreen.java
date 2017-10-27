package com.imagecolletorsllc.imagecollectors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainScreen extends AppCompatActivity {
    //Init vars
    private static ListView list;
    private static ArrayList<String> photoShootList = new ArrayList<>();
    private static ArrayAdapter<String> photoShootAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        //set list view and adapters to values
        list = (ListView) findViewById(R.id.ListOfPhotoshoots);
        //connect adapter to the list view
        photoShootAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, photoShootList);
        list.setAdapter(photoShootAdapter);
        //set click listener to display client info on click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id){
                System.out.println("CLICKED LIST VIEW " + String.valueOf(position));
                goToClientView(position);
            }
        });
        updatePhotoShootList(this.getApplicationContext());
    }

    @Override
    protected void onStart(){
        super.onStart();
        //update the list of clients
        updatePhotoShootList(this.getApplicationContext());
    }

    @Override
    protected void onResume(){
        super.onResume();
        //update the list of clients
        updatePhotoShootList(this.getApplicationContext());
    }
    //go to the client view based on the client number
    private void goToClientView(int pos){
        //sets intent to the client activity
        Intent intent = new Intent(this, ClientActivity.class);
        //creates a bundle to store the client info data
        Bundle b = new Bundle();
        //load json from local file
        String jsonString = Utils.loadJSONFromAsset(this);

        try{
            //check if jsonstring is not null
            if(jsonString != null || jsonString != "") {
                //creates json object from local json file string
                JSONObject jsonFull = new JSONObject(jsonString);

                //loads client json depending on the id
                JSONObject json = new JSONObject(jsonFull.getString(String.valueOf(pos + 1)));
                //puts data into the bundle by grabbing from the json
                b.putString("name", json.getString("clientName"));
                b.putString("email", json.getString("clientEmail"));
                b.putString("background", json.getString("clientBackground"));
                b.putString("phone", json.getString("clientPhoneNumber"));
                //put the bundle data into the intent
                intent.putExtras(b);
                //start the activity based on the intent
                startActivity(intent);
            }
        }catch(JSONException jsonEx){
            //print json errors
            jsonEx.printStackTrace();
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, SelectionScreen.class);
        startActivity(intent);
    }

    private void launchEntryActivity(){
        //sets the intended activity/screen
        Intent intent = new Intent(this, EntryActivity.class);
        //start the activity based on intent
        startActivity(intent);
    }
    //updates the photoshoot list
    public static void updatePhotoShootList(Context context){
        try {
            //get string of json from file
            String jsonString = Utils.loadJSONFromAsset(context);
            Log.i("asdf", jsonString + "ryan");
            //String jsonWithoutTrailing = jsonString.replaceAll("\\s+","");
            //check if string is null
            if(jsonString != null){
                //load json object from the json string
                JSONObject json = new JSONObject(jsonString);
                //create iterator to iterate over the json object
                Iterator<String> keysIterator = json.keys();
                //clear the listview
                photoShootAdapter.clear();
                //update the listview
                photoShootAdapter.notifyDataSetChanged();
                //iterate through the json iterator object
                while (keysIterator.hasNext())
                {
                    //get the json key
                    String keyStr = keysIterator.next();
                    //get the json value
                    String valueStr = json.getString(keyStr);
                    //create json object based on the json value
                    JSONObject jsonInJson = new JSONObject(valueStr);
                    //get the clients name from the json
                    String clientName = jsonInJson.getString("clientName");
                    //add client to the listview
                    photoShootList.add(keyStr + " " + clientName);
                }
                //update the list view
                photoShootAdapter.notifyDataSetChanged();
            }else{

            }
        } catch(JSONException e){
            //print json errors
            e.printStackTrace();
        }
    }
}