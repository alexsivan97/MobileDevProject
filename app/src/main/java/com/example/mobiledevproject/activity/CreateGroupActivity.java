package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.model.Group;
import com.example.mobiledevproject.model.GroupCreate;

public class CreateGroupActivity extends AppCompatActivity {

    EditText nameEt;
    EditText descriptionEt;
    Button commitBtn;

    private static final String TAG = "CreateGroupActivity";

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
                GroupCreate info = getCreateInfo();
                Group group = new Group(info);

                //  传输数据的部分

//                //上传json格式数据
//                Gson gson = new Gson();
//                String jsonInfo= gson.toJson(group);
//                Log.i(TAG, "onClick: "+jsonInfo);
//
//                String url = "https://2019group10.applinzi.com/api/v1/circles/";
//                OkHttpClient okHttpClient = new OkHttpClient();
//                RequestBody requestBody = RequestBody.create(MediaType
//                        .parse("application/json; charset=utf-8"), jsonInfo);
//                Request request= new Request.Builder()
//                        .url(url)
//                        .post(requestBody)
//                        .build();
//
//                try {
//                    Response response = okHttpClient.newCall(request).execute();
//                    if(response.isSuccessful()){
//                        Log.i(TAG, "onClick: success");
//                    }
//                } catch (IOException e){
//                    e.printStackTrace();
//                }


                Intent intent = new Intent();
                intent.putExtra("group_info", info);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private GroupCreate getCreateInfo(){
        return new GroupCreate(nameEt.getText().toString(), descriptionEt.getText().toString());
    }
}
