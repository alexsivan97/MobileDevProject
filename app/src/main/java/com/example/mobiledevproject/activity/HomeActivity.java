package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.BodyVpAdapter;
import com.example.mobiledevproject.adapter.ListRcvAdapter;
import com.example.mobiledevproject.config.StorageConfig;
import com.example.mobiledevproject.config.WebConfig;
import com.example.mobiledevproject.fragment.ExploreFragment;
import com.example.mobiledevproject.fragment.HomeFragment;
import com.example.mobiledevproject.fragment.MyFragment;
import com.example.mobiledevproject.model.GroupCreate;
import com.example.mobiledevproject.model.UserCreate;
import com.example.mobiledevproject.util.Utility;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    //  来自登录界面的信息，这里先写成静态数据
    public UserCreate user;


    ListRcvAdapter adapter;
    List<GroupCreate> infoList;

    //  添加fragment列表
    List<Fragment> fragList;

    @BindView(R.id.vp_home_body)
    ViewPager bodyVp;
    @BindView(R.id.nav_home_bottom)
    BottomNavigationView bottomBnv;

    MenuItem menuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        userInfoInit();
        viewPagerInit();
        navigationInit();
        bodyVp.setCurrentItem(0);
    }


    //  目前是静态实现
    private void userInfoInit(){
        //  从intent中读取数据
        user = new UserCreate("zx", "123");
        Intent intent = new Intent();
//        UserCreate user = (UserCreate)intent.getSerializableExtra("userinfo");
//        Log.i(TAG, "userInfoInit: "+user.getUserName());
//        user.setToken("");
        //  从intent中读取token
        Utility.setData(HomeActivity.this, StorageConfig.SP_KEY_TOKEN, WebConfig.TOKEN);

    }

    private void viewPagerInit() {
        fragList = new ArrayList<>();
        fragList.add(HomeFragment.newInstance());
        fragList.add(ExploreFragment.newInstance());
        fragList.add(MyFragment.newInstance(user));
        bodyVp.setAdapter(new BodyVpAdapter(getSupportFragmentManager(), fragList));

        bodyVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //  让底部按钮随着页面的滑动一起变化

                if(menuItem!=null){
                    menuItem.setChecked(false);
                } else {
                    bottomBnv.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomBnv.getMenu().getItem(position);
                menuItem.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void navigationInit() {

        //  这里只是设置点击哪个item后，给viewpager在fraglist中选择一个item
        bottomBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_home_navigation_home:
                        Log.i(TAG, "onNavigationItemSelected: home");
                        bodyVp.setCurrentItem(0);
                        return true;
                    case R.id.item_home_navigation_explore:
                        Log.i(TAG, "onNavigationItemSelected: explore");
                        bodyVp.setCurrentItem(1);
                        return true;
                    case R.id.item_home_navigation_my:
                        Log.i(TAG, "onNavigationItemSelected: my");
                        bodyVp.setCurrentItem(2);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

}
