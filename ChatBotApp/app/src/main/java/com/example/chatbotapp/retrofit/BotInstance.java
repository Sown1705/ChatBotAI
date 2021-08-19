package com.example.chatbotapp.retrofit;

import android.content.Context;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BotInstance {
    private static Retrofit retrofit;
    public static Retrofit getInstance(Context context){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://ai-chatbot.p.rapidapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
