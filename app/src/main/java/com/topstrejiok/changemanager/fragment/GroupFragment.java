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
import com.topstrejiok.changemanager.adapter.NameAdapter;

import java.util.ArrayList;


public class GroupFragment extends Fragment {
    private RecyclerView nameList;
    private ArrayList<String> names;
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
        names = new ArrayList<>();
        names.add("олег");
        names.add("лох");
        names.add("максим");
        names.add("тоже лох");
        nameAdapter = new NameAdapter(this.getContext(), names);
        nameList.setAdapter(nameAdapter);
    }
}
