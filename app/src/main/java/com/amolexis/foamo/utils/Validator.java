package com.amolexis.foamo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private String mEmail;
    private String mPassword;

    public Validator(String email, String password){
        mEmail = email;
        mPassword = password;
    }

    public boolean fieldIsEmpty(){
        if(mEmail.equals("") || mEmail.length() == 0 || mPassword.equals("") || mPassword.length() == 0){
            return true;
        }
        else return false;
    }

    public boolean emailIsValid(){
        //check pattern for email id
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(mEmail);
        return m.find();
    }

    public boolean passwordIsValid(){
        return mPassword.length()>5;
    }
}
