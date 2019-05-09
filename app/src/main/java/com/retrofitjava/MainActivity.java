package mvvm.com.retrofitjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.retrofitjava.apiinterface.UserService;
import com.retrofitjava.repo.RetrofitInstance;

import java.util.List;

import mvvm.com.retrofitjava.adapter.CharacterAdapter;
import mvvm.com.retrofitjava.model.Characters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getCanonicalName();
    RecyclerView recyclerView;
    CharacterAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserService userService = RetrofitInstance.getRetrofitInstance(UserService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Characters> call = userService.getUser("14872712c8a9d4589da8354a1818c97a","b6be349421e0be9a681cf3d0b5aa813a","123456");


        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");


        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                Log.d(TAG, "onResponse: " + response);
                Log.d(TAG, "onResponse: " + response.isSuccessful());
                Log.d(TAG, "onResponse: " + response.body());
                generateMarvelCharactesList(response.body().getData().getResults());
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d(TAG, "Error: " + t.getMessage());
            }

        });



    }

    private void generateMarvelCharactesList(List<Characters.DataBean.ResultsBean> response) {
        recyclerView = findViewById(R.id.recycler_view_notice_list);
        mAdapter = new CharacterAdapter(response);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
