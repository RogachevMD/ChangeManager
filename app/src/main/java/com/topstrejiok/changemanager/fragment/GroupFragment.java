package com.topstrejiok.changemanager.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.adapter.NameAdapter;


public class GroupFragment extends Fragment {
    private RecyclerView nameList;
    private NameAdapter nameAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView();
    }

    private void initRecyclerView(){
        nameList = this.getView().findViewById(R.id.names);
        nameList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        nameAdapter = new NameAdapter(this.getContext(), SessionActivity.names);
        nameList.setAdapter(nameAdapter);
    }
}
