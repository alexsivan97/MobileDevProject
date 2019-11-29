package com.example.mobiledevproject.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.ContentsVpAdapter;
import com.example.mobiledevproject.fragment.ManageFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckinActivity extends AppCompatActivity {

    @BindView(R.id.tl_checkin_funcs)
    TabLayout funcsTl;
    @BindView(R.id.vp_checkin_contents)
    ViewPager contentsVp;

    List<ManageFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        ButterKnife.bind(this);
        viewPagerInit();
        tabInit();
    }


    private void viewPagerInit() {
        fragmentList = new ArrayList<>();
        for(int i=0;i<3;++i){
            fragmentList.add(ManageFragment.newInstance("标题"+i, "内容"+i));
        }
        contentsVp.setAdapter(new ContentsVpAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void tabInit(){
        funcsTl.setupWithViewPager(contentsVp);
    }


}
