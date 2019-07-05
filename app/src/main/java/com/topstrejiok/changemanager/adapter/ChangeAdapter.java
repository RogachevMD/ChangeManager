package com.topstrejiok.changemanager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topstrejiok.changemanager.Libs.Group;
import com.topstrejiok.changemanager.R;

public class ChangeAdapter extends RecyclerView.Adapter<ChangeAdapter.ChangeVH> {

    @NonNull
    @Override
    public ChangeVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_change, viewGroup, false);
        return new ChangeVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeVH changeVH, int i) {
        changeVH.name1.setText(Group.Owns.get(i).from.Name);
        changeVH.name2.setText(Group.Owns.get(i).to.Name);
        changeVH.money.setText(String.valueOf(Group.Owns.get(i).money));
    }

    @Override
    public int getItemCount() {
        return Group.Owns.size();
    }

    class ChangeVH extends RecyclerView.ViewHolder{

        TextView name1;
        TextView name2;
        TextView money;
        public ChangeVH(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name1);
            name2 = itemView.findViewById(R.id.name2);
            money = itemView.findViewById(R.id.money);

        }
    }
}
