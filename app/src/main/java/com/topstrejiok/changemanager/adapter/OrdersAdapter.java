package com.topstrejiok.changemanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.model.NameItem;
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
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_orders, viewGroup, false);
        return new OrdersVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersVH ordersVH, int i) {
        ordersVH.orderName.setText(data.get(i).getItemName());
        ordersVH.orderPrice.setText(data.get(i).getItemPrice().toString());
        for (NameItem ni: data.get(i).getNames()){
            TextView cb = new TextView(context);
            cb.setText(ni.getName());
            ordersVH.nameHolder.addView(cb);
        }

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
            nameHolder = v.findViewById(R.id.namecontainer);

        }
    }
}
