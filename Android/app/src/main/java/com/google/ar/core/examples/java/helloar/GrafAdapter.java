package com.google.ar.core.examples.java.helloar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.core.examples.java.Model.Graffiti;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GrafAdapter extends RecyclerView.Adapter<GrafAdapter.MyViewHolder> {

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
