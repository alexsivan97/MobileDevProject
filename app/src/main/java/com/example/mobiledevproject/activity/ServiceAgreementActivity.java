package com.example.mobiledevproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiledevproject.R;

public class ServiceAgreementActivity extends AppCompatActivity {
    // Log打印的通用Tag
    private final String TAG = "ServiceAgreementActivity";

    //声明UI对象
    Button bt_agree = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_agreement);

        viewInit();
        viewSetOnClick();

    }

    private void viewInit() {
        bt_agree = findViewById(R.id.bt_agree);
    }

    private void viewSetOnClick() {
        bt_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it_serviceagreement_to_register = new Intent(ServiceAgreementActivity.this, RegisterActivity.class);
                startActivity(it_serviceagreement_to_register);
                finish();
            }

        });

    }
}
