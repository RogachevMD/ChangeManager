package com.topstrejiok.changemanager.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.model.NameItem;
import com.topstrejiok.changemanager.model.OrderItem;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersVH> {

    private Context context;
    private boolean foreach = false;
    View.OnClickListener radioClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton rb = (RadioButton) view;
            switch (rb.getId()) {
                case R.id.rbforeach:
                    foreach = true;
                    break;
                case R.id.rbforall:
                    foreach = false;
                    break;
            }
        }
    };

    public OrdersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_orders, viewGroup, false);
        return new OrdersVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersVH ordersVH, final int position) {

        ordersVH.orderName.setText(SessionActivity.ordersController.getOrderItems()
                .get(ordersVH.getAdapterPosition()).getItemName() + " " + position);
        ordersVH.orderPrice.setText(String.valueOf(SessionActivity.ordersController.getOrderItems()
                .get(ordersVH.getAdapterPosition()).getItemPrice()));

        ordersVH.nameHolder.removeAllViews();

        for (NameItem ni : SessionActivity.ordersController.getOrderItems()
                .get(ordersVH.getAdapterPosition()).getNames()) {
            if (ni.getChecked()) {
                ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(context)
                        .inflate(R.layout.item_name_2, null, false);
                TextView cb = v.findViewById(R.id.itemName);
                cb.setText(ni.getName());
                ordersVH.nameHolder.addView(v);
            }
        }

        ordersVH.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.text_are_you_sure);
                builder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton(R.string.text_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SessionActivity.ordersController.
                                getOrderItems().remove(position);
                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        ordersVH.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View v = LayoutInflater.from(context).inflate(R.layout.alert_item_orders, null);
                final LinearLayout cbgroup = v.findViewById(R.id.checkboxgroup);
                final EditText ordername = v.findViewById(R.id.edtname);
                final EditText orderprice = v.findViewById(R.id.edtprice);
                final RadioButton rbfe = v.findViewById(R.id.rbforeach);
                final RadioButton rbfa = v.findViewById(R.id.rbforall);
                rbfe.setOnClickListener(radioClick);
                rbfa.setOnClickListener(radioClick);
                OrderItem OIMAIN = SessionActivity.ordersController.getOrderItems().get(position);
                ((CheckBox) v.findViewById(R.id.cbselectall)).setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                for (int i = 0; i < cbgroup.getChildCount(); i++) {
                                    View v = cbgroup.getChildAt(i);
                                    if (v instanceof CheckBox) {
                                        ((CheckBox) v).setChecked(b);
                                    }
                                }
                            }
                        });
                ordername.setText(OIMAIN.getItemName());
                orderprice.setText(OIMAIN.getItemPrice().toString());
                if (OIMAIN.getForeach()) {
                    foreach = true;
                    rbfe.setChecked(true);
                    rbfa.setChecked(false);
                } else {
                    foreach = false;
                    rbfa.setChecked(true);
                    rbfe.setChecked(false);
                }

                for (NameItem ni : OIMAIN.getNames()) {
                    CheckBox cb = new CheckBox(context);
                    cb.setText(ni.getName());
                    cb.setChecked(ni.getChecked());
                    cbgroup.addView(cb);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.text_edit_order);
                builder.setPositiveButton(R.string.text_edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OrderItem OI = new OrderItem("Order", 0.0, foreach, null);

                        ArrayList<NameItem> namess = new ArrayList<>();
                        for (NameItem ni : SessionActivity.ordersController.getOrderItems().get(position).getNames()) {
                            NameItem newni = ni.GetClone();
                            namess.add(newni);
                        }

                        for (int i = 0; i < cbgroup.getChildCount(); i++) {
                            View v = cbgroup.getChildAt(i);
                            if (v instanceof CheckBox) {
                                if (((CheckBox) v).isChecked()) {
                                    namess.get(i).setChecked(true);
                                } else {
                                    namess.get(i).setChecked(false);
                                }
                            }
                        }
                        OI.setNames(namess);
                        if (!ordername.getText().toString().equals("")) {
                            OI.setItemName(ordername.getText().toString());
                        }
                        if (!orderprice.getText().toString().equals("")) {
                            OI.setItemPrice(Double.valueOf(orderprice.getText().toString()));
                        }


                        SessionActivity.ordersController.getOrderItems().set(position, OI);
                        dialog.dismiss();
                        //SessionActivity.ordersController.PrintOrders();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setView(v);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return SessionActivity.ordersController.getOrderItems().size();
    }

    public void UpdateView() {

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
