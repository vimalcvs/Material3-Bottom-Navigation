package com.vimalcvs.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.vimalcvs.bottomnavigation.fragment.HomeFragment;
import com.vimalcvs.bottomnavigation.fragment.RecentFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.vimalcvs.bottomnavigation.fragment.SettingFragment;
import com.vimalcvs.bottomnavigation.helper.BottomNavItemSelectedListener;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MaterialToolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ViewPager2 viewPager = findViewById(R.id.view_pager);

    viewPager.setUserInputEnabled(false);
    AppFragmentPageAdapter adapter = new AppFragmentPageAdapter(this);
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getItemCount() - 1);

    BottomNavigationView navigation = findViewById(R.id.navigation);
    BottomNavItemSelectedListener listener = new BottomNavItemSelectedListener(viewPager, toolbar);
    navigation.setOnItemSelectedListener(listener);
  }

  static class AppFragmentPageAdapter extends FragmentStateAdapter {
    public AppFragmentPageAdapter(AppCompatActivity appCompatActivity) {
      super(appCompatActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      switch (position) {
        case 0:
          return HomeFragment.newInstance();
        case 1:
          return RecentFragment.newInstance();
        case 2:
          return SettingFragment.newInstance();
        default:
          throw new RuntimeException("Not supported");
      }
    }
    @Override
    public int getItemCount() {
      return 3;
    }
  }
}
