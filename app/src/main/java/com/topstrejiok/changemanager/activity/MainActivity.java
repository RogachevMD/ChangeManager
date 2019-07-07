package com.topstrejiok.changemanager.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topstrejiok.changemanager.Controller.SessionController;
import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.adapter.SessionAdapter;
import com.topstrejiok.changemanager.model.SessionListItem;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_SESSIONS = "KEY_SESSIONS";

    SharedPreferences mPrefs;

    private RecyclerView SessionRecyclerView;
    private SessionAdapter sessionAdapter ;
    private FloatingActionButton addSessionButton;
    //private ArrayList<SessionListItem> data = new ArrayList<>();
    public static SessionController sessionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sessions");
        Init();
        initRecyclerView();
    }

    private void Init() {
        sessionController = loadData();
        addSessionButton = findViewById(R.id.AddSession);
        addSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Session");
                final View view = getLayoutInflater().inflate(R.layout.alert_item_session, null);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!((TextView) view.findViewById(R.id.AlertName))
                                .getText().toString().equals("")) {
                            sessionController.getSessionItem().add(
                                    new SessionListItem(((TextView) view.findViewById(R.id.AlertName))
                                    .getText().toString(),
                                    System.currentTimeMillis()));
                        }
                        dialog.dismiss();
                        saveData();
                        sessionController = loadData();
                        sessionAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setView(view);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


    private void initRecyclerView() {
        SessionRecyclerView = findViewById(R.id.SessionView);
        SessionRecyclerView.setHasFixedSize(true);
        SessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sessionAdapter = new SessionAdapter(this);
        SessionRecyclerView.setAdapter(sessionAdapter);
    }

    //FIXME говна

    private void saveData() {
        mPrefs = getSharedPreferences("ASSA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(sessionController);
        mPrefs.edit().putString(KEY_SESSIONS, json).apply();
    }

    private SessionController loadData() {
        mPrefs = getSharedPreferences("ASSA",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(KEY_SESSIONS, "");
        if (json.equals("")) {
            return new SessionController();
        } else {
            return gson.fromJson(json, new TypeToken<SessionController>(){}.getType());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}
