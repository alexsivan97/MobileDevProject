package com.example.mobiledevproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.interfaces.GetFragmentInfo;

import java.util.List;

public class ContentsVpAdapter extends FragmentPagerAdapter {

    List<GetFragmentInfo> fragmentList;
    public ContentsVpAdapter(FragmentManager fm, List<GetFragmentInfo> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentList.get(position).getTitle();
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return (Fragment)fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
