package com.example.mobiledevproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.model.Group;
import com.example.mobiledevproject.model.GroupCreate;
import com.example.mobiledevproject.model.User;
import com.example.mobiledevproject.model.UserCreate;
import com.example.mobiledevproject.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
                UserCreate user = new UserCreate("zx", "123");
                String url = "https://zxzx.applinzi.com/api/v1/auth/login";

                //上传json格式数据
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                String jsonInfo= gson.toJson(user);
                Log.i(TAG, "onClick: "+jsonInfo);

                HttpUtil.postOkHttpRequest(url, jsonInfo, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseBody = response.body().string();
                        Log.i(TAG, "onResponse: "+ responseBody);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CreateGroupActivity.this, "获得响应",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


//                Intent intent = new Intent();
//                intent.putExtra("group_info", info);
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });
    }

    private GroupCreate getCreateInfo(){
        return new GroupCreate(nameEt.getText().toString(), descriptionEt.getText().toString());
    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            String url = "https://zxzx.applinzi.com/api/v1/auth/login";
            User user = new User("zx", "123");
            //  传输数据的部分

            //上传json格式数据
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonInfo= gson.toJson(user);
            HttpUtil.postOkHttpRequest(url, jsonInfo, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i(TAG, "onResponse: "+response.body().string());
                }
            });
        }
    };
}
