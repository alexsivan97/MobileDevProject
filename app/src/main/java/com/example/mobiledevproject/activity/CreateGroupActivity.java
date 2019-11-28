package com.example.mobiledevproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.model.GroupCreate;

public class CreateGroupActivity extends AppCompatActivity {

    EditText nameEt;
    EditText descriptionEt;
    Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        viewInit();
        viewSetOnClick();

    }

    private void viewInit(){
        nameEt = findViewById(R.id.et_cg_name);
        descriptionEt = findViewById(R.id.et_cg_description);
        commitBtn = findViewById(R.id.btn_cg_commit);
    }

    private void viewSetOnClick(){
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private GroupCreate getCreateInfo(){
        return new GroupCreate(nameEt.getText().toString(), descriptionEt.getText().toString());
    }
}
