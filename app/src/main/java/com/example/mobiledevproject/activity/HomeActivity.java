package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.google.android.material.card.MaterialCardView;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    TextView createGroupTv;
    MaterialCardView groupsMcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewInit();
        viewSetOnClick();
    }

    private void viewInit(){
        createGroupTv = findViewById(R.id.tv_home_create_group);
        groupsMcv = findViewById(R.id.mcv_home_groups);
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


}
