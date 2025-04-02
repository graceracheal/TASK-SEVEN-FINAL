package com.example.taskseven2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeScreen;
    ProfileFragment profileScreen;
    SettingsFragment settingsScreen;
    NotificationsFragment notificationsScreen;
    Fragment currentScreen;
    BottomNavigationView navBar;
    FragmentContainerView screenArea;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupScreenLayout();
        createScreens();
        addScreensToApp();
        setupNavBarClicks();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    private void setupScreenLayout() {
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        screenArea = new FragmentContainerView(this);
        screenArea.setId(1001);
        LinearLayout.LayoutParams screenParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f);
        screenArea.setLayoutParams(screenParams);
        navBar = new BottomNavigationView(this);
        navBar.setId(1002);
        navBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        navBar.inflateMenu(R.menu.bottom_nav_menu);
        navBar.setBackgroundColor(Color.BLACK);
        navBar.setItemIconTintList(getResources().getColorStateList(R.color.bottom_nav_icon_color, getTheme()));
        navBar.setItemTextColor(getResources().getColorStateList(R.color.bottom_nav_icon_color, getTheme()));
        mainLayout.addView(screenArea);
        mainLayout.addView(navBar);
        setContentView(mainLayout);
    }

    private void createScreens() {
        homeScreen = new HomeFragment();
        profileScreen = new ProfileFragment();
        settingsScreen = new SettingsFragment();
        notificationsScreen = new NotificationsFragment();
        currentScreen = homeScreen;
    }

    private void addScreensToApp() {
        androidx.fragment.app.FragmentTransaction changes = getSupportFragmentManager().beginTransaction();
        changes.add(screenArea.getId(), homeScreen);
        changes.add(screenArea.getId(), profileScreen);
        changes.add(screenArea.getId(), settingsScreen);
        changes.add(screenArea.getId(), notificationsScreen);

        changes.hide(profileScreen);
        changes.hide(settingsScreen);
        changes.hide(notificationsScreen);
        changes.commit();
        navBar.setSelectedItemId(R.id.navigation_home);
    }

    private void setupNavBarClicks() {
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment screenToShow = null;

                if (item.getItemId() == R.id.navigation_home) {
                    screenToShow = homeScreen;
                }
                else if (item.getItemId() == R.id.navigation_profile) {
                    screenToShow = profileScreen;
                }
                else if (item.getItemId() == R.id.navigation_settings) {
                    screenToShow = settingsScreen;
                }
                else if (item.getItemId() == R.id.navigation_notifications) {
                    screenToShow = notificationsScreen;
                }
                if (screenToShow != null && screenToShow != currentScreen) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(currentScreen)
                            .show(screenToShow)
                            .commit();
                    currentScreen = screenToShow;
                    return true;
                }

                return false;
            }
        });
    }
}