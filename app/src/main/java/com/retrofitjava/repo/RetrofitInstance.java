package com.retrofitjava.repo;

import com.retrofitjava.apiinterface.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/";

    /**
     * Create an instance of Retrofit object
     *
     **/
    public static UserService getRetrofitInstance(Class<UserService> userServiceClass) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit.create(userServiceClass);
    }
}
