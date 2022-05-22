package com.google.ar.core.examples.java.adapters;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.core.examples.java.helloar.databinding.ItemContainerReceivedMessageBinding;
import com.google.ar.core.examples.java.models.ChatMessage;

public class ChatAdapter {

    private final Bitmap receiverProfileImage;

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerReceivedMessageBinding binding;
        SentMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding){
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }
    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding){
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage chatMessag, Bitmap receiverProfileImage){
            binding.textMessage.setText(chatMessag.message);
            binding.textMessage.setText(chatMessag.dateTime);

        }
    }
}
