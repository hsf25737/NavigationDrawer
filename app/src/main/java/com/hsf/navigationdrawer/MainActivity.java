package com.hsf.navigationdrawer;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.hsf.navigationdrawer.databinding.ActivityMainBinding;

/**
 * NavigationView和Toolbar联动
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0) {

            @Override
            public void syncState() {
                super.syncState();
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                return super.onOptionsItemSelected(item);
            }

            @Override
            public void setHomeAsUpIndicator(Drawable indicator) {
                super.setHomeAsUpIndicator(indicator);
            }

            @Override
            public void setHomeAsUpIndicator(int resId) {
                super.setHomeAsUpIndicator(resId);
            }

            @Override
            public boolean isDrawerIndicatorEnabled() {
                return super.isDrawerIndicatorEnabled();
            }

            @Override
            public void setDrawerIndicatorEnabled(boolean enable) {
                super.setDrawerIndicatorEnabled(enable);
            }

            @NonNull
            @Override
            public DrawerArrowDrawable getDrawerArrowDrawable() {
                return super.getDrawerArrowDrawable();
            }

            @Override
            public void setDrawerArrowDrawable(@NonNull DrawerArrowDrawable drawable) {
                super.setDrawerArrowDrawable(drawable);
            }

            @Override
            public boolean isDrawerSlideAnimationEnabled() {
                return super.isDrawerSlideAnimationEnabled();
            }

            @Override
            public void setDrawerSlideAnimationEnabled(boolean enabled) {
                super.setDrawerSlideAnimationEnabled(enabled);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                toggle.syncState();
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }

            @Override
            public View.OnClickListener getToolbarNavigationClickListener() {
                return super.getToolbarNavigationClickListener();
            }

            @Override
            public void setToolbarNavigationClickListener(View.OnClickListener onToolbarNavigationClickListener) {
                super.setToolbarNavigationClickListener(onToolbarNavigationClickListener);
            }
        };
        drawer.addDrawerListener(toggle);

        //三条横杠和返回箭头切换的toggle
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 重写返回键方法
     * 若抽屉在打开状态，点返回键，只关抽屉，不退出程序。
     */
    @Override
    public void onBackPressed() {
        toggle.syncState();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        toggle.syncState();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}