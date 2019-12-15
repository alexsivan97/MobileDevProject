package com.example.mobiledevproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.interfaces.GetFragmentInfo;
import com.example.mobiledevproject.model.GroupCreate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IntroFragment extends Fragment implements GetFragmentInfo {


    Unbinder unbinder;
    String title;
    GroupCreate info;
    @BindView(R.id.tv_intro_master)
    TextView tvIntroMaster;
    @BindView(R.id.tv_intro_desc)
    TextView tvIntroDesc;
    @BindView(R.id.tv_intro_time)
    TextView tvIntroTime;
    @BindView(R.id.tv_intro_rule)
    TextView tvIntroRule;

    private static final String TAG = "IntroFragment";

    public IntroFragment() {
        // Required empty public constructor
    }

    //  单例模式
    public static IntroFragment newInstance(String title, GroupCreate info) {
        IntroFragment fragment = new IntroFragment();
        fragment.title = title;
        fragment.info = info;
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
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        unbinder = ButterKnife.bind(this, view);
        viewInit();

        return view;
    }

    private void viewInit(){
        try {
            Log.i(TAG, "viewInit: "+info.toString());

//            tvIntroMaster.setText(info.getMasterId());
//            tvIntroDesc.setText(info.getDescription());
//            String seTime = info.getStartAt()+"~"+info.getEndAt();
//            tvIntroTime.setText(seTime);
//            tvIntroRule.setText(info.getCheckRule());

        } catch (Exception e){
//            GroupActivity groupActivity = (GroupActivity)getActivity();
//            info = groupActivity.group;
//            viewInit();
        }

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
