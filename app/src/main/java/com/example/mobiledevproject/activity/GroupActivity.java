package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.ContentsVpAdapter;
import com.example.mobiledevproject.MyApp;
import com.example.mobiledevproject.fragment.CircleFragment;
import com.example.mobiledevproject.fragment.GroupCheckinFragment;
import com.example.mobiledevproject.fragment.IntroFragment;
import com.example.mobiledevproject.fragment.ManageFragment;
import com.example.mobiledevproject.interfaces.GetFragmentInfo;
import com.example.mobiledevproject.model.Group;
import com.example.mobiledevproject.model.User;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupActivity extends AppCompatActivity {
    final static String TAG = "GroupActivity";
    @BindView(R.id.tl_checkin_funcs)
    TabLayout funcsTl;
    @BindView(R.id.vp_checkin_contents)
    ViewPager contentsVp;

    List<GetFragmentInfo> fragmentList;

    @BindView(R.id.iv_checkin_groupicon)
    ImageView groupIconIv;
    @BindView(R.id.tv_checkin_name)
    TextView nameTv;
    @BindView(R.id.tv_checkin_membernum)
    TextView memberNumTv;
    @BindView(R.id.btn_checkin)
    Button checkinBtn;

    public Group group;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);

//        viewInit();
        groupInit();
        viewPagerInit();
        tabInit();

        MyApp app = (MyApp)getApplication();
        user = app.getUser();
        Log.i(TAG, "onCreate: "+user.toString());


        checkinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, CheckinActivity.class);
                startActivity(intent);
            }
        });

    }


    private void viewPagerInit() {
        fragmentList = new ArrayList<>();
        fragmentList.add(IntroFragment.newInstance("简介", group));
        fragmentList.add(GroupCheckinFragment.newInstance("动态", "内容"));
        fragmentList.add(CircleFragment.newInstance("圈子", "内容"));
        fragmentList.add(ManageFragment.newInstance("管理", "内容"));

        contentsVp.setAdapter(new ContentsVpAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void tabInit() {
        funcsTl.setupWithViewPager(contentsVp);
    }

    private void groupInit(){
        if(group==null){
            intentReceived();
        }
    }

    private void intentReceived() {
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("group_info");
        viewSetInfo(group);
    }

    private void viewSetInfo(Group group) {

        nameTv.setText(group.getGroupName());

//        nameTv.setText("aa");
        //  此处成员数据要通过数据库读取
        memberNumTv.setText("成员"+group.getMemberList().size()+"人");

        //  此处小组头像要通过数据库读取
//        groupIconIv.setImageResource();

    }

}
