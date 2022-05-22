package com.google.ar.core.examples.java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.ar.core.examples.java.helloar.R;
import com.google.ar.core.examples.java.helloar.databinding.ActivityChatBinding;
import com.google.ar.core.examples.java.models.User;
import com.google.ar.core.examples.java.utilities.Constants;

public class ChatMessageActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User receiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
        loadReceiverDetails();
    }
    private void loadReceiverDetails(){
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.textName.setText(receiverUser.name);
    }
    private void setListener(){
        binding.imageBack.setOnClickListener(view -> onBackPressed());
    }
}