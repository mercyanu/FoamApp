package com.amolexis.foamo.api.model;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("username")
    private String mUsername;
    @SerializedName("password")
    private String mPassword;

    public Login(String username, String password){
        this.mUsername = username;
        this.mPassword = password;
    }
}
