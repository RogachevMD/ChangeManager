package com.topstrejiok.changemanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.topstrejiok.changemanager.Libs.Group;
import com.topstrejiok.changemanager.Libs.Person;
import com.topstrejiok.changemanager.adapter.SessionAdapter;
import com.topstrejiok.changemanager.model.SessionListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView SessionRecyclerView;
    private SessionAdapter sessionAdapter;

    private FloatingActionButton addSessionButton;

    private ArrayList<SessionListItem> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        Group.People.add(new Person(1, "oleg", 450, 700));
        Group.People.add(new Person(2, "artem", 300, 0));
        Group.People.add(new Person(3, "hleb", 200, 250));
        //Group.People.Add(new Person(4, "kek", 1000, 0));
        Group.Calculate();
        Group.OutOwns();
        Init();
    }

    private void Init()
    {
        addSessionButton = findViewById(R.id.AddSession);
        addSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("kek");
                final View view = getLayoutInflater().inflate(R.layout.alert_item_session,null);

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        data.add(  new SessionListItem(((TextView)view.findViewById(R.id.AlertName)).getText().toString() ,System.currentTimeMillis()) );
                    }
                });
                builder.setView(view);
                AlertDialog alert = builder.create();

                alert.show();
            }
        });

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
