package com.amolexis.foamo.ui.controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amolexis.foamo.R;
import com.amolexis.foamo.utils.CustomToast;
import com.amolexis.foamo.utils.Network;
import com.amolexis.foamo.utils.Validator;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment {


    public LoginFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragV = inflater.inflate(R.layout.fragment_login, container);

        Button logButton = (Button) fragV.findViewById(R.id.login_button);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText getUsermail = (EditText) fragV.findViewById(R.id.useremail);
                EditText getPword = (EditText) fragV.findViewById(R.id.pword);
                String email = getUsermail.getText().toString().trim();
                String password = getPword.getText().toString().trim();

                Validator validate = new Validator(email, password);

                boolean internetIsEnabled = Network.checkInternet(getContext());

                if(internetIsEnabled){
                    //validate email and password is valid and not empty
                    if(!validate.fieldIsEmpty()){
                        if(validate.emailIsValid()){
                            if(validate.passwordIsValid()){
                               //send to API and get response
                                Toast.makeText(getContext(), "Connecting to API, please wait!", Toast.LENGTH_LONG);

                            }else new CustomToast().displyToast(getContext(),fragV, "Password must be 6 or more chars");
                        }else new CustomToast().displyToast(getContext(), fragV, "Enter a valid email");
                    }else new CustomToast().displyToast(getContext(), fragV, "All fields are required");
                }else new CustomToast().displyToast(getContext(),fragV, "No internet connection");
            }
        });

        return fragV;
    }
}
