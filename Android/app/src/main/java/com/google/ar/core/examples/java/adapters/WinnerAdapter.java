package com.google.ar.core.examples.java.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.core.examples.java.Model.Graffiti;
import com.google.ar.core.examples.java.Model.Winner;
import com.google.ar.core.examples.java.helloar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.WinnerViewHolder> {
    public static Context context;
    ArrayList<Winner> winnerList;

    public WinnerAdapter(Context context, ArrayList<Winner> winnerList) {
        this.context = context;
        this.winnerList = winnerList;
    }

    @NonNull
    @Override
    public WinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.winner_item,parent, false);
        WinnerViewHolder winnerViewHolder = new WinnerViewHolder(v);
        return new WinnerViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WinnerViewHolder holder, int position) {

        Winner winner = winnerList.get(position);
        holder.winnerCreateur.setText(String.valueOf(winner.getCreateur()));
        holder.winnerlike.setText(winner.getLike().toString());
        holder.winDate.setText(StampToDate(winner.getDate()));

        String NFTUri = null;
        String grafWinnerUri = null;
        NFTUri = winner.getNFT();
        grafWinnerUri = winner.getGraf();
        Picasso.get().load(NFTUri).into(holder.NFT);
        Picasso.get().load(grafWinnerUri).into(holder.grafImageWinner);
    }

    @Override
    public int getItemCount() {
        return winnerList.size();
    }

    public static class WinnerViewHolder extends RecyclerView.ViewHolder {

        TextView winnerlike, winnerCreateur, winDate;
        ImageView grafImageWinner, NFT;

        public WinnerViewHolder(@NonNull View itemView) {
            super(itemView);
            winnerlike = itemView.findViewById(R.id.winnerLike);
            winnerCreateur = itemView.findViewById(R.id.winnerCreateur);
            winDate = itemView.findViewById(R.id.winDate);
            grafImageWinner = itemView.findViewById(R.id.grafImageWinner);
            NFT = itemView.findViewById(R.id.NFT);
        }
    }
    private String StampToDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.FRENCH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}
