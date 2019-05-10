package com.retrofitjava.apiinterface;


import io.reactivex.Single;
import mvvm.com.retrofitjava.model.Characters;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserService {
//?ts={ts_key}&apikey={api_key}&hash={hash_key
        @GET("characters")
        Single<Characters> getUser(@Query("apikey") String clientId, @Query("hash") String hashkey,
                                   @Query("ts") String ts);

}


