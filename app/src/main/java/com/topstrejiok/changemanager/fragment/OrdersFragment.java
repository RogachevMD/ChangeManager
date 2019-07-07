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

    @Override
    public void onResume() {
        super.onResume();
        //itemList.removeAllViews();
        //itemList.setAdapter(ordersAdapter);
        ordersAdapter.notifyDataSetChanged();
        ordersAdapter.UpdateView();
    }


    private void init() {
        floatingActionButton = getView().findViewById(R.id.addorders);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FIXME еще говна
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
                                for (int i = 0; i < cbgroup.getChildCount(); i++) {
                                    View v = cbgroup.getChildAt(i);
                                    if (v instanceof CheckBox) {
                                        ((CheckBox) v).setChecked(b);
                                    }
                                }
                            }
                        });

                for (NameItem ni : SessionActivity.ordersController.getNameItems()) {
                    CheckBox cb = new CheckBox(getContext());
                    cb.setText(ni.getName());
                    cb.setChecked(ni.getChecked());
                    cbgroup.addView(cb);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.text_add_order);
                builder.setPositiveButton(R.string.text_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OrderItem OI = new OrderItem("Order", 0.0, foreach, null);

                        //ArrayList<NameItem> namess = new ArrayList<>();
                        //namess.addAll(SessionActivity.ordersController.getNameItems());

                        //ArrayList<NameItem> namess = ( ArrayList<NameItem>)SessionActivity.ordersController.getNameItems().clone();
                        ArrayList<NameItem> namess = new ArrayList<>();
                        for (NameItem ni:SessionActivity.ordersController.getNameItems())
                        {
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
                        SessionActivity.ordersController.getOrderItems().add(OI);
                        ordersAdapter = new OrdersAdapter(getContext());
                        itemList.setAdapter(ordersAdapter);
                        dialog.dismiss();
                        SessionActivity.ordersController.PrintOrders();
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

    private void initRecyclerView() {
        itemList = this.getView().findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ordersAdapter = new OrdersAdapter(this.getContext());
        itemList.setAdapter(ordersAdapter);
    }


}
