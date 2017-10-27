package com.imagecolletorsllc.imagecollectors.database;

/**
 * Created by nturner on 9/12/17.
 */

public class PhotoShootData {

    private String clientName;
    private String clientEmail;
    private String clientAddress;
    private long photoshootId;

    public PhotoShootData(long id, String name, String email, String address){
        clientName = name;
        clientEmail = email;
        clientAddress = address;
        photoshootId = id;
    }
    public PhotoShootData(){}


    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

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

    public long getPhotoshootId() {
        return photoshootId;
    }

    public void setPhotoshootId(long photoshootId) {
        this.photoshootId = photoshootId;
    }
}
