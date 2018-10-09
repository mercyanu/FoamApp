package com.amolexis.foamo.ui.landing;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amolexis.foamo.R;
import com.amolexis.foamo.api.model.Login;
import com.amolexis.foamo.api.model.User;
import com.amolexis.foamo.api.service.APIClient;
import com.amolexis.foamo.api.service.Service;
import com.amolexis.foamo.utils.CustomToast;
import com.amolexis.foamo.utils.Network;
import com.amolexis.foamo.utils.Validator;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment {

    //shared preferences may be used
    public static final String USERNAME = "";
    public static final String PASSWORD = "";

    //Retrofit retrofit = APIClient.getClient();
    //Service service = retrofit.create(Service.class);



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
                               //create json format
                                /*JSONObject jsonFormat = new JSONObject();
                                try{
                                    //String forJson = "\"username\":\""+username+"\",\"password\":"+password;
                                    //return new JSONObject(forJson);

                                    jsonFormat.put("username", email);
                                    jsonFormat.put("password", password);
                                    new CustomToast().displyToast(getContext(), fragV, jsonFormat.getString("username")+jsonFormat.getString("password"));

                                    //sendToAPI(fragV, jsonFormat);
                                }catch(Exception e){e.printStackTrace();} */

                                APIClient client = new APIClient();
                                Service apiService = client.getClient().create(Service.class);
                                Call<User> call = apiService.login(new Login(email, password));

                                call.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.isSuccessful()) {
                                            new CustomToast().displyToast(getContext(), fragV, response.body().getMessage()+"\n Token: "+response.body().getAccessToken());
                                        }else{
                                            new CustomToast().displyToast(getContext(), fragV, "Incorrect username or password");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });

                                //sendToAPI(fragV, jsonLogin);
                                //new CustomToast().displyToast(getContext(), fragV, "Connecting to API");
                                //Toast.makeText(getContext(), "Connecting to API, please wait!", Toast.LENGTH_LONG);

                            }else new CustomToast().displyToast(getContext(),fragV, "Password must be 6 or more chars");
                        }else new CustomToast().displyToast(getContext(), fragV, "Enter a valid email");
                    }else new CustomToast().displyToast(getContext(), fragV, "All fields are required");
                }else new CustomToast().displyToast(getContext(),fragV, "No internet connection");
            }
        });

        return fragV;
    }

    private void sendToAPI(final View v, JSONObject jsonLogin){
        //Login userCred = new Login(email, password);
            APIClient apiClient = new APIClient();
            Service apiService = apiClient.getClient().create(Service.class);
            Call<User> call = apiService.login(new Login("efef","dfwd"));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                        new CustomToast().displyToast(getContext(), v, "Error:"+response.code()+"  "+response.body().getMessage()+
                                "Token is: "+response.body().getAccessToken());
                    }
                    else{
                        new CustomToast().displyToast(getContext(), v, "Error: "+response.code()+" "+
                                response.headers()+ "...Incorrect username or password!");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    new CustomToast().displyToast(getContext(), v, "Server unreachable, Please retry again later");
                }
            });

    }
}
