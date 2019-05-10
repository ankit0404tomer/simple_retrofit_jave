package com.retrofitjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.retrofitjava.adapter.CharacterAdapter;
import com.retrofitjava.apiinterface.UserService;
import com.retrofitjava.repo.RetrofitInstance;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvvm.com.retrofitjava.model.Characters;

import java.util.List;

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
        Single<Characters> observable =
                userService.getUser("14872712c8a9d4589da8354a1818c97a", "b6be349421e0be9a681cf3d0b5aa813a", "123456");
        userService.getUser("14872712c8a9d4589da8354a1818c97a", "b6be349421e0be9a681cf3d0b5aa813a", "123456");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Characters>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Characters characters) {
                        generateMarvelCharactesList(characters.getData().getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

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
