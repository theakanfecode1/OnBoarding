package com.epump.remis.remisonboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;

public class OnBoardingViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public OnBoardingViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        mFragments = fragments;
    }


//    public OnBoardingViewPagerAdapter(@NonNull Fragment fragment, ArrayList<Fragment> fragments) {
//        super(fragment);
//        mFragments = fragments;
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }


//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return mFragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return mFragments.size();
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        return null;
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
}
