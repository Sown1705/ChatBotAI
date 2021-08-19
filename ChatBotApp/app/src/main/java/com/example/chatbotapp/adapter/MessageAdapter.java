package com.example.chatbotapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbotapp.databinding.ItemMessBinding;
import com.example.chatbotapp.model.Chatbot;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    List<Chatbot> list;

    public void setList(List<Chatbot> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMessBinding binding = ItemMessBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setMess(list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (list!=null) return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemMessBinding binding;
        public ViewHolder(ItemMessBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
