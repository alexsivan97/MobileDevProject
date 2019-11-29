package com.example.mobiledevproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.activity.CheckinActivity;
import com.example.mobiledevproject.model.GroupCreate;

import java.util.List;

public class ListRcvAdapter extends RecyclerView.Adapter<ListRcvAdapter.GroupViewHolder> {

    private static final String TAG = "ListRcvAdapter";

    private Context context;
    private List<GroupCreate> infoList;

    public ListRcvAdapter(Context context, List<GroupCreate> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);


        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        GroupCreate group = infoList.get(position);
        holder.groupNameTv.setText(group.getGroupName());
        holder.descriptionTv.setText(group.getDescription());

        //  可以封装一下
        holder.descriptionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: "+position);
                Intent intent = new Intent(context, CheckinActivity.class);
                context.startActivity(intent);

//                GroupCreate group = infoList.get(position);
//                Log.i(TAG, "onClick: this group is "+group.getGroupName());
            }
        });



    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    //  添加数据
    public void addData(GroupCreate createdGroup, int position){
        infoList.add(position, createdGroup);
        Log.i(TAG, "addData: new item");
        notifyItemInserted(position);
    }

    public void addData(int position){
        infoList.add(position, new GroupCreate("te", "te"));
        Log.i(TAG, "addData: new item");
        notifyItemInserted(position);
    }


    public static class GroupViewHolder extends RecyclerView.ViewHolder{
        TextView groupNameTv;
        TextView descriptionTv;
        View itemView;

        public GroupViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            groupNameTv = itemView.findViewById(R.id.tv_group_name);
            descriptionTv = itemView.findViewById(R.id.tv_group_description);
        }
    }

}
