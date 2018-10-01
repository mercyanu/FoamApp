package com.amolexis.foamo.ui.landing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.amolexis.foamo.R;
import com.amolexis.foamo.utils.CustomToast;
import com.amolexis.foamo.utils.Network;

public class LandingActivity extends AppCompatActivity {

    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_layout);

        view = findViewById(R.id.landing_viewGroup);
        boolean isInternetEnabled = Network.checkInternet(this);
        if(isInternetEnabled){
            new CustomToast().displyToast(getApplicationContext(), view, " You have Internet");
        }
        else{
            new CustomToast().displyToast(getApplicationContext(), view, " No internet connection");
        }
    }
}
