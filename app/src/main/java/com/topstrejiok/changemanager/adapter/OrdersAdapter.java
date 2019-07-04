package com.topstrejiok.changemanager.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public void onBindViewHolder(@NonNull final OrdersVH ordersVH, int position) {
        ordersVH.orderName.setText(data.get(ordersVH.getAdapterPosition()).getItemName());
        ordersVH.orderPrice.setText(String.valueOf(data.get(ordersVH.getAdapterPosition()).getItemPrice()));

        for (NameItem ni : data.get(ordersVH.getAdapterPosition()).getNames()) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(context)
                    .inflate(R.layout.item_name_2, null, false);
            TextView cb = v.findViewById(R.id.itemName);
            ImageView iv = v.findViewById(R.id.delete);
            cb.setText(ni.getName());
            ordersVH.nameHolder.addView(v);
            Log.i("KEK", ni.getName());
        }

        ordersVH.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are You sure?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data.remove(ordersVH.getAdapterPosition());
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Log.i("KEK", data.get(ordersVH.getAdapterPosition()).getNames().size() + "");
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
