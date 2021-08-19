package com.example.chatbotapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chatbotapp.adapter.MessageAdapter;
import com.example.chatbotapp.databinding.ActivityMainBinding;
import com.example.chatbotapp.model.Chatbot;
import com.example.chatbotapp.model.Messsenger;
import com.example.chatbotapp.retrofit.BotInstance;
import com.example.chatbotapp.retrofit.MyAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MessageAdapter adapter;
    String userId="";
    List<Chatbot> list = new ArrayList<>();
    Retrofit retrofit ;
    MyAPI service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new MessageAdapter();
        retrofit = BotInstance.getInstance(this);
        service = retrofit.create(MyAPI.class);
        userId = getIntent().getStringExtra("user");
        Log.e("id",userId);
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtMess.getText().toString().isEmpty()){
                    String data = binding.edtMess.getText().toString();
                    getMessage(userId,data);
                    binding.recyclerView.setHasFixedSize(true);
                    binding.recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                }
                else {
                    binding.edtMess.setError("Plz enter name");
                }
            }

        });

    }

    private void getMessage(String uid,String message){
        Observable<Messsenger> observable = service.getMessage(uid,message);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Messsenger>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("onSubscribe",""+d);
                    }

                    @Override
                    public void onNext(@NonNull Messsenger messsenger) {
                        Log.e("onNext",""+messsenger.getChatbot().getResponse());
                        list.add(messsenger.getChatbot());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError",""+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete","onComplete");
                        adapter.setList(list);
                        binding.recyclerView.setAdapter(adapter);
                    }
                });
    }
}