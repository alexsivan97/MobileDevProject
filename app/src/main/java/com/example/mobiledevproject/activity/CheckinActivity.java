package com.example.mobiledevproject.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.PhotoAdapter;
import com.example.mobiledevproject.model.MessageBean;
import com.example.mobiledevproject.util.GlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckinActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private static final int REQUEST_CODE_CHOOSE = 23;
    final static  String TAG = "CheckinActivity";
    List<String> imagePath;
    private MessageBean  messageBean;
    private ImageButton backIBtn;
    private Button sendBtn;
    private EditText contentET;
    private GridView imageGV;
    PhotoAdapter photoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        initView();
        loadData();
        photoAdapter =  new PhotoAdapter(CheckinActivity.this,imagePath);
        imageGV.setAdapter(photoAdapter);
    }
    protected void initView() {
        backIBtn = findViewById(R.id.checkin_back_ibtn);
        sendBtn = findViewById(R.id.checkin_send_btn);
        contentET = findViewById(R.id.checkin_content_et);
        imageGV = findViewById(R.id.checkin_image_gv);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = "0011";
                String content = contentET.getText().toString();
                imagePath.remove("PHOTO_TAKING");
                List<String> localImages = imagePath;
                List<String> onlineImages = null;
                if (content == null && localImages == null) {

                } else {
                    //获取提交时间作为文件名
                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String time = dateFormat.format(date);
                    messageBean =  new MessageBean(userId,content,localImages,onlineImages,time);
                    String path = userId;
                    try {
                        List<MessageBean> messageBeanList;
                        String AbsolutePath = getFilesDir().toString();
                        File file = new File(AbsolutePath +"/"+ path);
                        if (file.exists()) {
                            FileInputStream fileInputStream = openFileInput(path);
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                            messageBeanList = (ArrayList)objectInputStream.readObject();
                            fileInputStream.close();
                            objectInputStream.close();
                        } else {
                            messageBeanList = new ArrayList<>();
                        }
                        messageBeanList.add(0,messageBean);
                        FileOutputStream fileOutputStream = openFileOutput(path, Context.MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(messageBeanList);
                        fileOutputStream.close();
                        objectOutputStream.close();
                        System.out.println("=================hhhhhhhhhhhhhhhhhhh============");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    imagePath.clear();
                    Intent intent = new Intent(CheckinActivity.this,GroupActivity.class);
                    startActivity(intent);
                }
            }
        });

        backIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == imagePath.size() - 1){
                Matisse.from(CheckinActivity.this)
                        .choose(MimeType.ofAll())
                        .countable(true)
                        .maxSelectable(7 - imagePath.size())
//                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
//                        .showPreview(false) // Default is `true`
                        .forResult(REQUEST_CODE_CHOOSE);

            }else {
                imagePath.remove(position);
                loadAdapter();
            }
            }
        });

    }
    protected void loadData() {

        imagePath =  new ArrayList<>();
        imagePath.add("PHOTO_TAKING");
        if (ContextCompat.checkSelfPermission(CheckinActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CheckinActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    System.exit(0);
                }

        }
    }















    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> paths = Matisse.obtainPathResult(data);
            imagePath.remove("PHOTO_TAKING");
            imagePath.addAll(paths);
            imagePath.add("PHOTO_TAKING");
            loadAdapter();
        }

    }
    private void loadAdapter() {
        photoAdapter = new PhotoAdapter(CheckinActivity.this,imagePath);
        imageGV.setAdapter(photoAdapter);

    }


}
