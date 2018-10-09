package com.amolexis.foamo.api.service;

import com.amolexis.foamo.api.model.Login;
import com.amolexis.foamo.api.model.User;
import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {
    @POST("/login")
    Call<User> login(@Body Login login);

    @GET("/current")
    Call<List<User>> getCurrentNews(@Header("Authorization") String accessToken);
}
