package com.imagecolletorsllc.imagecollectors;

/**
 * Created by nturner on 9/13/17.
 */

public class Client {


    //create client vars
    private String clientName;
    private String clientBackground;
    private String clientEmail;
    private String clientPhone;
    private long id;
    private boolean hasPhone;

    //client constructor without phone
    public Client(String cname, String cemail, String cbackground){
        //init vars
        clientName = cname;
        clientBackground = cbackground;
        clientEmail = cemail;
        clientPhone = "";
        hasPhone = false;
    }

    //client constructor with phone
    public Client(String cname, String cemail, String cbackground, String cphone){
        //init vars
        clientName = cname;
        clientBackground = cbackground;
        clientEmail = cemail;
        clientPhone = cphone;
        hasPhone = true;
    }

    //variable getter and setters +++++
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientBackground() {
        return clientBackground;
    }

    public void setClientBackground(String clientBackground) { this.clientBackground = clientBackground; }

    public boolean isHasPhone() {
        return hasPhone;
    }

    public void setHasPhone(boolean hasPhone) {
        this.hasPhone = hasPhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //+++++++
}
