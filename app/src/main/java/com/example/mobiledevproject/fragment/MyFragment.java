package com.example.mobiledevproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.model.User;

import butterknife.BindView;


public class MyFragment extends Fragment {

    User user;
    @BindView(R.id.tv_my_username)
    TextView tvMyUsername;
    @BindView(R.id.tv_my_userdescription)
    TextView tvMyUserdescription;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance(User user) {
        MyFragment fragment = new MyFragment();
        fragment.user = user;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    private void setUserInfo(){
        tvMyUsername.setText(this.user.getUserId());
    }


}
