package com.topstrejiok.changemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.topstrejiok.changemanager.model.ListItem;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter {

    private ArrayList<ListItem> data;
    private Context context;

    public ListAdapter(Context context, ArrayList<ListItem> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(@NonNull View v) {
            super(v);
        }
    }
}
