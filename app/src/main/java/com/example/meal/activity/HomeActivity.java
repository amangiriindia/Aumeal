package com.example.meal.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meal.R;
import com.example.meal.databinding.ActivityHomeBinding;
import com.example.meal.fragment.HomeFragment;
import com.example.meal.fragment.TimeFragment;
import com.example.meal.fragment.TomorrowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();  // Get the selected item's ID

                // Check which item was selected and handle accordingly
                if (itemId == R.id.home) {
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (itemId == R.id.tomorrow) {
                    replaceFragment(new TomorrowFragment());
                    return true;
                } else if (itemId == R.id.time) {
                    replaceFragment(new TimeFragment());
                    return true;
                }

                // Return false for any other case
                return false;
            }
        });


        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
