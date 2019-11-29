package com.example.mobiledevproject.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.fragment.ManageFragment;

import java.util.List;

public class ContentsVpAdapter extends FragmentPagerAdapter {

    List<ManageFragment> fragmentList;
    public ContentsVpAdapter(FragmentManager fm, List<ManageFragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
