package com.example.chatbotapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messsenger {

    @SerializedName("chatbot")
    @Expose
    private Chatbot chatbot;

    public Chatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

}