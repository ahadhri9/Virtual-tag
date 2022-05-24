package com.google.ar.core.examples.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.ar.core.examples.java.Model.Winner;
import com.google.ar.core.examples.java.helloar.R;
import com.google.ar.core.examples.java.adapters.WinnerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WinnerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    WinnerAdapter winnerAdapter;
    ArrayList<Winner> winnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        ImageButton returnBtn = findViewById(R.id.returnWinBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(WinnerActivity.this, GalleryActivity.class);
                startActivity(I);
                finish();
            }
        });

        recyclerView = findViewById(R.id.winners);
        database = FirebaseDatabase.getInstance().getReference("Winners");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        winnerList = new ArrayList<>();
        winnerAdapter = new WinnerAdapter(this, winnerList);
        recyclerView.setAdapter(winnerAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Winner winner = dataSnapshot.getValue(Winner.class);
                    winnerList.add(winner);
                }
                winnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}