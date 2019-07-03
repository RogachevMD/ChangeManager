package com.topstrejiok.changemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.model.OrderItem;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersVH> {

    private Context context;
    private ArrayList<OrderItem> data;

    public OrdersAdapter(Context context, ArrayList<OrderItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public OrdersVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVH ordersVH, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class OrdersVH extends RecyclerView.ViewHolder {

        TextView orderName;
        TextView orderPrice;
        ImageView delete;
        ImageView edit;
        LinearLayout nameHolder;

        OrdersVH(@NonNull View v) {
            super(v);
            orderName = v.findViewById(R.id.ordername);
            orderPrice = v.findViewById(R.id.orderprice);
            delete = v.findViewById(R.id.delete);
            edit = v.findViewById(R.id.edit);

        }
    }
}
