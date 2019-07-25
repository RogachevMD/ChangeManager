package com.topstrejiok.changemanager.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.controller.OrdersController;
import com.topstrejiok.changemanager.fragment.ChangeFragment;
import com.topstrejiok.changemanager.fragment.GroupFragment;
import com.topstrejiok.changemanager.fragment.OrdersFragment;
import com.topstrejiok.changemanager.fragment.ReceiptFragment;

public class SessionActivity extends AppCompatActivity {

    public static OrdersController ordersController = new OrdersController();
    final FragmentManager fm = getSupportFragmentManager();
    private FrameLayout fragmentContainer;
    private SharedPreferences sharedPreferences;
    public static String mainKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void init() {
        ordersController = loadData();
        BottomNavigationView navigationView = findViewById(R.id.bottomnavbar);
        fragmentContainer = findViewById(R.id.fragmentcontainer);
        fm.beginTransaction().add(fragmentContainer.getId(), new OrdersFragment(), "1")
                .commit();
        setTitle(R.string.text_orders);
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        fragmentContainer.removeAllViews();
                        switch (menuItem.getItemId()) {
                            case R.id.action_orders:
                                setTitle(R.string.text_orders);
                                fm.beginTransaction().add(fragmentContainer.getId(), new OrdersFragment(), "1")
                                        .commit();
                                return true;
                            case R.id.action_group:
                                setTitle(R.string.text_group);
                                fm.beginTransaction().add(fragmentContainer.getId(), new GroupFragment(), "2")
                                        .commit();
                                return true;
                            case R.id.action_money:
                                setTitle(R.string.text_change);
                                fm.beginTransaction().add(fragmentContainer.getId(), new ChangeFragment(), "3")
                                        .commit();
                                return true;
                            case R.id.action_photo:
                                setTitle(R.string.text_receipt);
                                fm.beginTransaction().add(fragmentContainer.getId(),new ReceiptFragment(),"4")
                                        .commit();
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void saveData() {
        mainKey = getIntent().getStringExtra("kekshrek");
        sharedPreferences = getSharedPreferences(mainKey, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(ordersController);
        sharedPreferences.edit().putString(mainKey, json).apply();
    }

    private OrdersController loadData() {
        mainKey = getIntent().getStringExtra("kekshrek");
        sharedPreferences = getSharedPreferences(mainKey, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(mainKey, "");
        if (json.equals("")) {
            return new OrdersController();
        } else {
            return gson.fromJson(json, new TypeToken<OrdersController>() {
            }.getType());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }
}
