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
import android.widget.Button;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;
import com.topstrejiok.changemanager.adapter.ChangeAdapter;


public class ChangeFragment extends Fragment {
    private  Button btn;
    private RecyclerView recyclerView;
    private ChangeAdapter changeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView = getView().findViewById(R.id.rvchange);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btn =  getView().findViewById(R.id.get_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionActivity.sessionController.Calculate();
                recyclerView.setAdapter(new ChangeAdapter());
            }
        });
    }

/*    public void Kek(View view)
    {
        SessionActivity.sessionController.Calculate();
    }*/
}
