package com.example.chatbotapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.example.chatbotapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =   DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtUid.getText().toString().isEmpty()){
                    String  uid= binding.edtUid.getText().toString();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("user",uid);
                    startActivity(intent);
                }
                else {
                    binding.edtUid.setError("Plz enter name");
                }
            }
        });
    }

}