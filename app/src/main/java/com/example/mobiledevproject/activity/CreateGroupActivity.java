package com.example.mobiledevproject.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.config.API;
import com.example.mobiledevproject.config.StorageConfig;
import com.example.mobiledevproject.model.Group;
import com.example.mobiledevproject.model.GroupCreate;
import com.example.mobiledevproject.model.UserCreate;
import com.example.mobiledevproject.util.HttpUtil;
import com.example.mobiledevproject.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateGroupActivity extends AppCompatActivity {

    @BindView(R.id.et_cg_name)
    EditText etCgName;
    @BindView(R.id.et_cg_description)
    EditText etCgDescription;
    @BindView(R.id.btn_cg_startat)
    Button btnCgStartat;
    @BindView(R.id.tv_cg_startat)
    TextView tvCgStartat;
    @BindView(R.id.btn_cg_endat)
    Button btnCgEndat;
    @BindView(R.id.tv_cg_endat)
    TextView tvCgEndat;
    @BindView(R.id.btn_cg_commit)
    Button btnCgCommit;

    private Calendar calendar;

    private static final String TAG = "CreateGroupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();

        viewSetOnClick();

    }

    private void viewSetOnClick() {
        btnCgStartat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(CreateGroupActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i(TAG, "onClick: " + hourOfDay);
                        Log.i(TAG, "onClick: " + minute);
                        tvCgStartat.setText(hourOfDay+":"+minute);
//                        calendar.setTimeInMillis(System.currentTimeMillis());
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
                    }
                }, hour, minute, true).show();
            }
        });

        btnCgEndat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(CreateGroupActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i(TAG, "onClick: " + hourOfDay);
                        Log.i(TAG, "onClick: " + minute);
                        tvCgEndat.setText(hourOfDay+":"+minute);
//                        calendar.setTimeInMillis(System.currentTimeMillis());
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
                    }
                }, hour, minute, true).show();
            }
        });

        btnCgCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

                GroupCreate info = getCreateInfo();
                Group group = new Group(info);

                Intent intent = new Intent();
                UserCreate user = (UserCreate)intent.getSerializableExtra("user");


                //上传json格式数据
                String userInfo = gson.toJson(user);
                String groupInfo = gson.toJson(group);

                Log.i(TAG, "onClick: " + userInfo);
                Log.i(TAG, "onClick: " + groupInfo);

                HttpUtil.postOkHttpRequest(API.CIRCLE, groupInfo, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseBody = response.body().string();
                        Log.i(TAG, "onResponse: " + responseBody);

                        JsonObject jsonObject;


                        try {
                            jsonObject = (JsonObject) new JsonParser().parse(responseBody);
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                JsonObject data = jsonObject.get("data").getAsJsonObject();
                                String accessToken = data.get("accessToken").getAsString();
                                Log.i(TAG, "onResponse: " + accessToken);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CreateGroupActivity.this, "获得响应",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CreateGroupActivity.this, "请重试",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CreateGroupActivity.this, "请重试",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });


                intent.putExtra("group_info", info);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case -1:
                    Log.i(TAG, "handleMessage: "+(String)msg.obj);
                    break;
                case 1:
                    String token = (String)msg.obj;
                    updateToken(token);
                    break;
            }
        }
    };

    public void updateToken(String token){
        Utility.setData(CreateGroupActivity.this, StorageConfig.SP_KEY_TOKEN, token);
    }

    private GroupCreate getCreateInfo() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setGroupName(etCgName.getText().toString());
        groupCreate.setDescription(etCgDescription.getText().toString());
        groupCreate.setStartAt(tvCgStartat.getText().toString());
        groupCreate.setEndAt(tvCgEndat.getText().toString());

        return groupCreate;
    }

}
