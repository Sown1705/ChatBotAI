package com.example.chatbotapp.retrofit;

import com.example.chatbotapp.model.Messsenger;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MyAPI {
    //https://ai-chatbot.p.rapidapi.com/chat/free?message=hello&uid=user1
    @Headers({
            "x-rapidapi-key:1d460bf1d3msh4ca3dd5addbb8cep1e2ed5jsnf1117b71b7b8",
            "x-rapidapi-host:ai-chatbot.p.rapidapi.com"
    })
    @GET("/chat/free")
    Observable<Messsenger> getMessage(@Query("uid") String uid,
                                      @Query("message") String message);
}
