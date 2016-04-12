package com.codesky.userguider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class HomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager vPager = (ViewPager) findViewById(R.id.guider_pager);
        VPageAdapter vpAdapter = new VPageAdapter(getSupportFragmentManager());
        vPager.setAdapter(vpAdapter);
    }

    public class VPageAdapter extends FragmentPagerAdapter {

        public VPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DFragment();
                case 1:
                    return new VFragment();
                case 2:
                    return new DMFragment();
                case 3:
                    return new VMFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.dialog_guide_title);
                case 1:
                    return getString(R.string.view_guide_title);
                case 2:
                    return getString(R.string.dialog_multi_guide_title);
                case 3:
                    return getString(R.string.view_multi_guide_title);
                default:
                    return null;
            }
        }
    }
}
