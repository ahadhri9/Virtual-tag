package com.google.ar.core.examples.java.helloar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.examples.java.GalleryActivity;
import com.google.ar.core.examples.java.Model.Graffiti;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GrafAdapter extends RecyclerView.Adapter<GrafAdapter.MyViewHolder> {

    public Bitmap bitmapGraf;
    String actualGrafImage;
    Integer actualGrafLike;
    Context context;
    ArrayList<Graffiti> graffitiList;

    public GrafAdapter(Context context, ArrayList<Graffiti> graffitiList) {
        this.context = context;
        this.graffitiList = graffitiList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grafitem,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Graffiti graffiti = graffitiList.get(position);
        holder.pseudoCreateur.setText(String.valueOf(graffiti.getCreateur()));
        holder.nbLike.setText(String.valueOf(graffiti.getLike()));

        String imageUri = null;
        imageUri = graffiti.getImage();
        Picasso.get().load(imageUri).into(holder.grafImage);

        /*actualGrafImage = graffitiList.get(position).getImage();
        actualGrafLike = graffitiList.get(position).getLike();
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(actualGrafImage);
        try {
            localFile = File.createTempFile("tempImage", ".png");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e("teub", bitmapGraf);
                            bitmapGraf = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            Log.e("teub1", + bitmapGraf);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(new GalleryActivity(), "Failed to retrieve", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "onPageSelected: " + localFile);
        Toast.makeText(HelloArActivity.this, "Nombre de like" + actualGrafLike, Toast.LENGTH_SHORT).show();

        holder.itemView.setOnClickListener();*/
    }

    @Override
    public int getItemCount() {
        return graffitiList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nbLike, pseudoCreateur;
        ImageView grafImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nbLike = itemView.findViewById(R.id.nbLike);
            pseudoCreateur = itemView.findViewById(R.id.pseudoCreateur);
            grafImage = itemView.findViewById(R.id.grafImage);

        }
    }
}
