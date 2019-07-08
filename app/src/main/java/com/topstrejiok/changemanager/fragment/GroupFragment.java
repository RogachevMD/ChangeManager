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
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.adapter.NameAdapter;


public class GroupFragment extends Fragment {
    private RecyclerView nameList;
    private NameAdapter nameAdapter;
    private FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        initRecyclerView();
    }

    private void initRecyclerView(){
        nameList = this.getView().findViewById(R.id.names);
        nameList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        nameAdapter = new NameAdapter(this.getContext());
        nameList.setAdapter(nameAdapter);
    }

    private void init(){
        floatingActionButton = this.getView().findViewById(R.id.addname);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.text_add_person);
                final View v = getLayoutInflater().inflate(R.layout.alert_item_session, null);
                builder.setPositiveButton(R.string.text_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!((TextView) v.findViewById(R.id.AlertName))
                                .getText().toString().equals("")){
                            SessionActivity.ordersController.addNewName(((TextView)v.findViewById(R.id.AlertName))
                                    .getText().toString());
                            nameAdapter.notifyDataSetChanged();
                        }

                        dialog.dismiss();
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
}
