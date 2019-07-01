package com.topstrejiok.changemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.topstrejiok.changemanager.adapter.SessionAdapter;
import com.topstrejiok.changemanager.model.SessionListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView SessionRecyclerView;
    private SessionAdapter sessionAdapter;

    private ArrayList<SessionListItem> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }
    private void initRecyclerView(){
        SessionRecyclerView = findViewById(R.id.SessionView);
        SessionRecyclerView.setHasFixedSize(true);
        SessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();
        data.add(new SessionListItem("ASSA", System.currentTimeMillis()));
        sessionAdapter = new SessionAdapter(data, this);
        SessionRecyclerView.setAdapter(sessionAdapter);
    }
}
