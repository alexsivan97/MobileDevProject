package com.example.mobiledevproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.adapter.BitmapAdapter;
import com.example.mobiledevproject.model.MessageBean;

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
    final static  String TAG = "CheckinActivity";
    List<Bitmap> data;
    private MessageBean  messageBean;
    private ImageButton backIBtn;
    private Button sendBtn;
    private EditText contentET;

    private GridView imageGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        initView();
        loadData();
        BitmapAdapter bitmapAdapter =  new BitmapAdapter(CheckinActivity.this,data);
        imageGV.setAdapter(bitmapAdapter);
    }
    protected void initView() {
        backIBtn = findViewById(R.id.checkin_back_ibtn);
        sendBtn = findViewById(R.id.checkin_send_btn);
        contentET = findViewById(R.id.checkin_content_et);
        imageGV = findViewById(R.id.checkin_image_gv);
        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userId = "004";
                String content = contentET.getText().toString();
                List<String> localImages = null;
                List<Bitmap> onlineImages = null;
                System.out.println(content);
                System.out.println("=================hhhhhhhhhhhhhhhhhhh============");
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
                        System.out.println(getFilesDir());

                        String AbsolutePath = getFilesDir().toString();

                        File file = new File(AbsolutePath +"/"+ path);
                        if (file.exists()) {
                            FileInputStream fileInputStream = openFileInput(path);
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                            messageBeanList = (ArrayList)objectInputStream.readObject();
                            System.out.println("++++++++++++++++++++++");
                            System.out.println(messageBeanList.size());
                            fileInputStream.close();
                            objectInputStream.close();
                        } else {
                            messageBeanList = new ArrayList<>();
                        }
                        messageBeanList.add(messageBean);
                        FileOutputStream fileOutputStream = openFileOutput(path, Context.MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(messageBeanList);
                        fileOutputStream.close();
                        objectOutputStream.close();
                        System.out.println("=================hhhhhhhhhhhhhhhhhhh============");
                    } catch (FileNotFoundException e) {

                    } catch (IOException e){
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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


    }
    protected void loadData() {
        data =  new ArrayList<>();

        System.out.println(Environment.getExternalStorageDirectory( ).getAbsolutePath());

        String fileName1 = "/storage/emulated/0/Download/b1.png";
        Bitmap bitmap1 = BitmapFactory.decodeFile(fileName1);
        data.add(bitmap1);
        String fileName2 = "/storage/emulated/0/Download/b2.png";
        Bitmap bitmap2 = BitmapFactory.decodeFile(fileName2);
        data.add(bitmap2);
    }
}
