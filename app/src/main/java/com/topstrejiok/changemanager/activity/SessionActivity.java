package com.topstrejiok.changemanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.topstrejiok.changemanager.Controller.OrdersController;
import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.fragment.ChangeFragment;
import com.topstrejiok.changemanager.fragment.GroupFragment;
import com.topstrejiok.changemanager.fragment.OrdersFragment;
import com.topstrejiok.changemanager.model.NameItem;

public class SessionActivity extends AppCompatActivity {

    public static OrdersController ordersController = new OrdersController();


    final Fragment fragmentOrder = new OrdersFragment();
    final Fragment fragmentGroup = new GroupFragment();
    final Fragment fragmentChange = new ChangeFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentOrder;
    private BottomNavigationView navigationView;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        init();
    }

    private void init() {
        navigationView = findViewById(R.id.bottomnavbar);
        fragmentContainer = findViewById(R.id.fragmentcontainer);
    /*    fm.beginTransaction().add(fragmentContainer.getId(), fragmentChange, "3")
                .hide(fragmentChange).commit();
        fm.beginTransaction().add(fragmentContainer.getId(), fragmentGroup, "2")
                .hide(fragmentGroup).commit();
        fm.beginTransaction().add(fragmentContainer.getId(), fragmentOrder, "1")
                .commit();*/
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        fragmentContainer.removeAllViews();
                        switch (menuItem.getItemId()) {
                            case R.id.action_orders:
                                fm.beginTransaction().add(fragmentContainer.getId(), new OrdersFragment(), "1")
                                        .commit();
                                return true;
                            case R.id.action_group:
                                fm.beginTransaction().add(fragmentContainer.getId(),  new GroupFragment(), "2")
                                        .commit();
                                return true;
                            case R.id.action_money:
                                fm.beginTransaction().add(fragmentContainer.getId(),  new ChangeFragment(), "3")
                                        .commit();
                                return true;
                        }
                        return false;
                    }
                });
        ordersController.getNameItems().add(new NameItem("Пенис"));
        ordersController.getNameItems().add(new NameItem("Детров"));
    }
}
