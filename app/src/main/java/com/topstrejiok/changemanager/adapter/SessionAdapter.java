package com.topstrejiok.changemanager.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.model.SessionListItem;

import java.util.ArrayList;


public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionVH> {

    private ArrayList<SessionListItem> data;
    private Context context;

    public SessionAdapter(ArrayList<SessionListItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public SessionVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_session_list, viewGroup, false);
        return new SessionVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionVH sessionVH, int i) {
        if (i % 2 == 1) {
            sessionVH.root.setBackgroundColor(context.getColor(R.color.colorFirst));
        } else {
            sessionVH.root.setBackgroundColor(context.getColor(R.color.colorSecond));
        }
        sessionVH.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SessionActivity.class);
                context.startActivity(intent);

            }
        });
        sessionVH.header.setText(data.get(i).getName());
        sessionVH.dateTime.setText(data.get(i).getDateTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SessionVH extends RecyclerView.ViewHolder {

        TextView header;
        TextView dateTime;
        ConstraintLayout root;
        ImageView delete;
        ImageView edit;

        SessionVH(@NonNull View v) {
            super(v);
            root = (ConstraintLayout) v;
            header = v.findViewById(R.id.header);
            dateTime = v.findViewById(R.id.dateTime);
            delete = v.findViewById(R.id.delete);
            edit = v.findViewById(R.id.edit);

        }
    }
}
