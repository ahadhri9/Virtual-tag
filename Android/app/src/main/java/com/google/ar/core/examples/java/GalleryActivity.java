package com.google.ar.core.examples.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.ar.core.examples.java.Model.Graffiti;
import com.google.ar.core.examples.java.helloar.GrafAdapter;
import com.google.ar.core.examples.java.helloar.HelloArActivity;
import com.google.ar.core.examples.java.helloar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    GrafAdapter grafAdapter;
    ArrayList<Graffiti> graffitiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ImageButton loupebtn = findViewById(R.id.loupebtn);
        loupebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(GalleryActivity.this, MapsActivity2.class);
                startActivity(I);
                finish();
            }
        });
        ImageButton importgraf = findViewById(R.id.addgrafbtn);
        importgraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(GalleryActivity.this, ImportGrafActivity.class);
                startActivity(I);
                finish();
            }
        });
        /*Button afficheButton = findViewById(R.id.afficheButton);
        afficheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(GalleryActivity.this, HelloArActivity.class);
                startActivity(I);
                finish();
            }
        });*/

        recyclerView = findViewById(R.id.gallery);
        database = FirebaseDatabase.getInstance().getReference("Graffitis");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        graffitiList = new ArrayList<>();
        grafAdapter = new GrafAdapter(this, graffitiList);
        recyclerView.setAdapter(grafAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Graffiti graffiti = dataSnapshot.getValue(Graffiti.class);
                    graffitiList.add(graffiti);
                }
                grafAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //recyclerView.
    }
}


















