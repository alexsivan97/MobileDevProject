package com.example.mobiledevproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.ListRcvAdapter;
import com.example.mobiledevproject.model.GroupCreate;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    RecyclerView demo;
    ListRcvAdapter adapter;
    Button buttondemo;

    List<GroupCreate> infoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        demo = findViewById(R.id.demo);
        buttondemo = findViewById(R.id.buttondemo);

        infoList = new ArrayList<GroupCreate>();

        //  添加一个静态数据测试
        GroupCreate test = new GroupCreate("testName21", "testDes2");
        infoList.add(test);
        GroupCreate test2 = new GroupCreate("testName222", "testDes222");
        infoList.add(test2);

        //  定义一个线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //  将管理器配置给recyclerView
        demo.setLayoutManager(manager);
        //  设置adapter
        adapter = new ListRcvAdapter(DemoActivity.this, infoList);
        //  添加adapter
        demo.setAdapter(adapter);
        //  添加动画
        demo.setItemAnimator(new DefaultItemAnimator());

        buttondemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData(infoList.size());
            }
        });

    }
}
