package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.ListRcvAdapter;
import com.example.mobiledevproject.model.GroupCreate;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    ListRcvAdapter adapter;

    TextView createGroupTv;
    MaterialCardView groupsMcv;
    RecyclerView listRcv;
    List<GroupCreate> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewInit();
        dataInit();
        initRecycleView();
        viewSetOnClick();
    }

    private void viewInit(){
        createGroupTv = findViewById(R.id.tv_home_create_group);
        groupsMcv = findViewById(R.id.mcv_home_groups);
        listRcv = findViewById(R.id.rcv_home_list);
    }

    private void dataInit(){

        //  此处应该从数据库中加载已经加入的小组
        infoList = new ArrayList<GroupCreate>();

    }

    private void viewSetOnClick(){
        groupsMcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: 添加一个圈子");
                Intent intent = new Intent(HomeActivity.this, CreateGroupActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void addGroupItem(GroupCreate createdGroup){
//        infoList.add(createdGroup);
        adapter.addData(createdGroup, infoList.size());

        Log.i(TAG, "addGroupItem: add an item");
        Log.i(TAG, "addGroupItem: list size "+infoList.size());
    }

    private void initRecycleView(){
        //  定义一个线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //  将管理器配置给recyclerView
        listRcv.setLayoutManager(manager);
        //  设置adapter
        adapter = new ListRcvAdapter(HomeActivity.this, infoList);
        //  添加adapter
        listRcv.setAdapter(adapter);
        //  添加动画
        listRcv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    GroupCreate createdGroup = (GroupCreate)data.getSerializableExtra("group_info");
                    Log.i(TAG, "onActivityResult: "+createdGroup.getGroupName());

                    //  新圈子信息添加到列表
                    addGroupItem(createdGroup);
                    //  刷新界面
                    //  刷新的方法写在adapter中了
                }
                break;
                default:
        }
    }

}
