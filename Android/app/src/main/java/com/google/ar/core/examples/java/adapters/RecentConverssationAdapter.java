package com.google.ar.core.examples.java.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.core.examples.java.ChatMessageActivity;
import com.google.ar.core.examples.java.Listener.ConversionListener;
import com.google.ar.core.examples.java.helloar.databinding.ItemContainerReceivedMessageBinding;
import com.google.ar.core.examples.java.helloar.databinding.ItemContainerRecentConversionBinding;
import com.google.ar.core.examples.java.models.ChatMessage;
import com.google.ar.core.examples.java.models.User;

import java.util.List;

public class RecentConverssationAdapter extends RecyclerView.Adapter<RecentConverssationAdapter.ConversionViewHolder>{

    private final List<ChatMessage> chatMessages;
    private final ConversionListener conversionListener;

    public RecentConverssationAdapter(List<ChatMessage> chatMessages , ConversionListener conversionListener) {
        this.chatMessages = chatMessages;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                ItemContainerRecentConversionBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));

    }

    @Override
    public int getItemCount() {

        return chatMessages.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder{
        ItemContainerRecentConversionBinding binding;

        ConversionViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding){
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textName.setText(chatMessage.conversionName);
            binding.textRecentMessage.setText(chatMessage.message);
            binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.getRoot().setOnClickListener(v->{
                User user=new User();
                user.id=chatMessage.conversionId;
                user.name=chatMessage.conversionName;
                user.image=chatMessage.conversionImage;
                conversionListener.onConversionClicked(user);
            });
        }
    }
    private Bitmap getConversionImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0, bytes.length);
    }
}

