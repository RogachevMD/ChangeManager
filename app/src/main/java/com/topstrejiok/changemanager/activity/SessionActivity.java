package com.topstrejiok.changemanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.fragment.ChangeFragment;
import com.topstrejiok.changemanager.fragment.GroupFragment;
import com.topstrejiok.changemanager.fragment.OrdersFragment;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {
    public static ArrayList<String> names;
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
        fm.beginTransaction().add(fragmentContainer.getId(), fragmentChange, "3")
                .hide(fragmentChange).commit();
        fm.beginTransaction().add(fragmentContainer.getId(), fragmentGroup, "2")
                .hide(fragmentGroup).commit();
        fm.beginTransaction().add(fragmentContainer.getId(), fragmentOrder, "1")
                .commit();
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_orders:
                                Log.d("Kek", "1");
                                fm.beginTransaction().hide(active).show(fragmentOrder).commit();
                                active = fragmentOrder;
                                return true;
                            case R.id.action_group:
                                fm.beginTransaction().hide(active).show(fragmentGroup).commit();
                                active = fragmentGroup;
                                Log.d("Kek", "2");
                                return true;
                            case R.id.action_money:
                                fm.beginTransaction().hide(active).show(fragmentChange).commit();
                                active = fragmentChange;
                                Log.d("Kek", "3");
                                return true;
                        }
                        return false;
                    }
                });
        names = new ArrayList<>();
        names.add("Владик");
        names.add("Олег");
        names.add("Максим");
        names.add("Владик1");
        names.add("Олег1");
        names.add("Максим1");
        names.add("Владик2");
        names.add("Олег2");
        names.add("Максим3");
        names.add("Владик4");
        names.add("Олег4");
        names.add("Максим4");
        names.add("Владик5");
        names.add("Олег5");
        names.add("Максим5");
        names.add("Владик6");
        names.add("Олег6");
        names.add("Максим6");
    }
}
