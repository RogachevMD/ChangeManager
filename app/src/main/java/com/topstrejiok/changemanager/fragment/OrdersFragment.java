package com.topstrejiok.changemanager.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.adapter.OrdersAdapter;
import com.topstrejiok.changemanager.model.NameItem;
import com.topstrejiok.changemanager.model.OrderItem;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView itemList;
    private OrdersAdapter ordersAdapter;
    private boolean foreach = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
        init();
    }

    private void init(){
        floatingActionButton = getView().findViewById(R.id.addorders);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Item");
                final View v = getLayoutInflater().inflate(R.layout.alert_item_orders, null);
                final LinearLayout cbgroup = v.findViewById(R.id.checkboxgroup);
                final EditText ordername = v.findViewById(R.id.edtname);
                final EditText orderprice = v.findViewById(R.id.edtprice);
                final RadioButton rbfe = v.findViewById(R.id.rbforeach);
                final RadioButton rbfa = v.findViewById(R.id.rbforall);
                rbfe.setOnClickListener(radioClick);
                rbfa.setOnClickListener(radioClick);
                ((CheckBox) v.findViewById(R.id.cbselectall)).setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        for(int i = 0; i < cbgroup.getChildCount(); i++){
                            View v = cbgroup.getChildAt(i);
                            if (v instanceof CheckBox){
                                ((CheckBox)v).setChecked(b);
                            }
                        }
                    }
                });
                for (NameItem ni: SessionActivity.names){
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(ni.getName());
                    cbgroup.addView(cb);
                }
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OrderItem oi;
                        String orderNames = "";
                        Double orderPrices = 0.0;
                        ArrayList<NameItem> namess = new ArrayList<>();
                        for(int i = 0; i < cbgroup.getChildCount(); i++){
                            View v = cbgroup.getChildAt(i);
                            if (v instanceof CheckBox){
                                if (((CheckBox) v).isChecked()){
                                    namess.add(SessionActivity.names.get(i));
                                }
                            }
                        }
                        if (ordername.getText().toString().equals("")){
                            orderNames = "Order";
                        }else {
                            orderNames = ordername.getText().toString();
                        }
                        if (!orderprice.getText().toString().equals("")){
                            orderPrices = Double.valueOf(orderprice.getText().toString());
                            /*SessionActivity.items.add(null);*/
                        }
                        OrderItem item = new OrderItem(orderNames,orderPrices,foreach,namess);
                        SessionActivity.items.add(item);
                        ordersAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

    private void initRecyclerView(){
        itemList = this.getView().findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ordersAdapter = new OrdersAdapter(this.getContext(), SessionActivity.items);
        itemList.setAdapter(ordersAdapter);
    }

    View.OnClickListener radioClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton rb = (RadioButton) view;
            switch (rb.getId()){
                case R.id.rbforeach:
                    foreach = true;
                    break;
                case R.id.rbforall:
                    foreach = false;
                    break;
            }
        }
    };
}
