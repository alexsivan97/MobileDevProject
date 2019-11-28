package com.example.mobiledevproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.model.GroupCreate;

import java.util.List;

public class ListRcvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<GroupCreate> infoList;

    public ListRcvAdapter(Context context, List<GroupCreate> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GroupCreate group = infoList.get(position);
        ((GroupViewHolder)holder).groupNameTv.setText(group.getGroupName());
        ((GroupViewHolder)holder).descriptionTv.setText(group.getDescription());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    //  添加数据
    public void addData(GroupCreate createdGroup, int position){
        infoList.add(position, createdGroup);
        notifyItemChanged(position);
    }

    public void addData(int position){
        infoList.add(position, new GroupCreate("te", "te"));
        notifyItemChanged(position);
    }


    private class GroupViewHolder extends RecyclerView.ViewHolder{
        private TextView groupNameTv;
        private TextView descriptionTv;

        public GroupViewHolder(View itemView){
            super(itemView);
            groupNameTv = itemView.findViewById(R.id.tv_group_name);
            descriptionTv = itemView.findViewById(R.id.tv_group_description);
        }
    }

}
