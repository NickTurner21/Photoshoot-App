package com.imagecolletorsllc.imagecollectors;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nturner on 9/13/17.
 */

public class Utils {

    public static Convention currentConvention;

    public static void setCurrentConvention(Convention c){
        currentConvention = c;
    }

    //Loads the string from the json file
    public static String loadJSONFromAsset(Context context) {
        //get photoshoot json file
        File file = new File(Environment.getExternalStorageDirectory(), "PhotoShoots.json");
        try {
            if (checkEmptyFile(file)){
                if(!file.exists()){
                    file.createNewFile();
                }
                return null;
            }
        }catch (IOException e){

        }
        try {
            //check if the file exists
            if(!file.exists()){
                //create the file if not exists
                file.createNewFile();
            }
            //create file input stream
            FileInputStream fis = new FileInputStream(file);
            //get byte length and create byte array
            byte[] data = new byte[(int) file.length()];
            //read file data into byte array
            fis.read(data);
            //close fileintputstream
            fis.close();
            //encode bytes into utf8 encoding
            String str = new String(data, "UTF-8");
            //return the json string
            return str;
        } catch (IOException ex) {
            //print ioexception read error
            ex.printStackTrace();
            //return nothing
            return null;
        }
    }
    public static boolean checkEmptyFile(final File file) throws IOException {
        if (FileUtils.readFileToString(file, "UTF8").trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
