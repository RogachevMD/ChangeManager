package com.topstrejiok.changemanager.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.model.NameItem;

public class OrdersFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
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
}
