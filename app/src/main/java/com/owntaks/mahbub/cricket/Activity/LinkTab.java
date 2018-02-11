package com.owntaks.mahbub.cricket.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.google.android.gms.ads.AdView;
import com.owntaks.mahbub.cricket.Fragment.Batsman;
import com.owntaks.mahbub.cricket.Fragment.Bowler;
import com.owntaks.mahbub.cricket.Fragment.Ranking;
import com.owntaks.mahbub.cricket.R;

import java.util.ArrayList;
import java.util.List;

public class LinkTab extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) this.mFragmentTitleList.get(position);
        }
    }

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_link_tab);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Batsman(), "Batsman");
        adapter.addFragment(new Bowler(), "Bowler");
        adapter.addFragment(new Ranking(), "Ranking");
        viewPager.setAdapter(adapter);
    }

    public void onBackPressed() {
        super.finish();
    }
}
