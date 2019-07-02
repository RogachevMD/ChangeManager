package com.topstrejiok.changemanager.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.adapter.NameAdapter;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {
    private RecyclerView nameList;
    private ArrayList<String> names;
    private NameAdapter nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addperson:
                AlertDialog.Builder builder = new AlertDialog.Builder(SessionActivity.this);
                builder.setTitle("Add Person");
                final View view = getLayoutInflater().inflate(R.layout.alert_item_session, null);

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        names.add(((TextView) view.findViewById(R.id.AlertName))
                                .getText().toString());
                    }
                });
                builder.setView(view);
                AlertDialog alert = builder.create();

                alert.show();
                return true;
            case R.id.deleteperson:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
        nameList = findViewById(R.id.names);
        nameList.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        names = new ArrayList<>();
        names.add("олех");
        names.add("лох");
        names.add("максим");
        names.add("тоже лох");
        nameAdapter = new NameAdapter(this, names);
        nameList.setAdapter(nameAdapter);
    }
}
