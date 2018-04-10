package com.epam.androidlab.task9_views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL =
            "https://developer.android.com/images/fundamentals/fragments.png?hl=ru";
    private String mImageAddress =
            "http://developer.alexanderklimov.ru/android/images/android_cat.jpg";
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        setToggle();

        navigationView = findViewById(R.id.nav_view);

        loadImage();
        setOnItemSelectedListener();
    }


    private void setToggle() {
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Действие home/up action bar'а должно открывать или закрывать drawer.
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void setOnItemSelectedListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.fragment1:
                        replaceFragment(new FirstFragment());
                        break;
                    case R.id.fragment2:
                        replaceFragment(new SecondFragment());
                        break;
                }
                setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
//                if (toggle.onOptionsItemSelected(item))
//                    return true;
                //loadImage();
                return true;
            }
        });
    }


    private void replaceFragment(Fragment newFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, newFragment)
                .commit();
    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void loadImage() {
        View header = navigationView.getHeaderView(0);
        ImageView ivFragments = header.findViewById(R.id.ivFragments);
        Glide.with(this)
             .load(IMAGE_URL)
             .into(ivFragments);
    }

//    private void loadImage() {
//
////        LayoutInflater inflater = LayoutInflater.from(this);
////        View layout = inflater.inflate(R.layout.nav_header, null);
////
////        ImageView ivFragments = layout.findViewById(R.id.ivFragments);
////
////        Glide.with(this)
////             .load(mImageAddress)
////             .into(ivFragments);
//
//        ImageView ivFragments = findViewById(R.id.ivFragments);
//
//        Glide.with(this)
//             .load(IMAGE_URL)
//             .into(ivFragments);
//    }
}
