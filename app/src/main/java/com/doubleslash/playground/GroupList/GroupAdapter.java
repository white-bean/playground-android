package com.doubleslash.playground.GroupList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.R;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> implements OnGroupItemClickListener{
    ArrayList<Group> items = new ArrayList<Group>();
    OnGroupItemClickListener listener;

    private Context context;

    public GroupAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.group_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group item = items.get(position);
        holder.setItem(context, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Group item){
        items.add(item);
    }

    public Group getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnGroupItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView location;
        ImageView category;
        TextView curr_num;
        TextView max_num;
        TextView name;
        TextView content;
        ImageView image_group;

        public ViewHolder(View itemView, final OnGroupItemClickListener listener){
            super(itemView);

            location = itemView.findViewById(R.id.location_tV);          // 위치
            category = itemView.findViewById(R.id.image_group_category); // 카테고리
            curr_num = itemView.findViewById(R.id.curr_tV);              // 현재 인원 수
            max_num = itemView.findViewById(R.id.total_tV);              // 전체 인원 수
            name = itemView.findViewById(R.id.subject_tV);               // 그룹 제목
            content = itemView.findViewById(R.id.info_tV);               // 그룹 소개
            image_group = itemView.findViewById(R.id.group_pic);            // 그룹 사진

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (listener != null){
                    listener.onItemClick(ViewHolder.this, view, position);
                }
            });
        }

        public void setItem(Context context, Group item) {
            location.setText(item.getLocation());

            switch (item.getCategory()) {
                case "스터디":
                    category.setImageResource(R.drawable.writing_hand);
                    category.setBackgroundResource(R.drawable.ic_button_study);
                    break;
                case "운동/다이어트":
                    category.setImageResource(R.drawable.diet);
                    category.setBackgroundResource(R.drawable.ic_button_diet);
                    break;
                case "문화생활":
                    category.setImageResource(R.drawable.draw);
                    category.setBackgroundResource(R.drawable.ic_button_cultural);
                    break;
                case "게임":
                    category.setImageResource(R.drawable.game);
                    category.setBackgroundResource(R.drawable.ic_button_game);
                    break;
                default:
                    category.setImageResource(R.drawable.ic_camera);
                    category.setBackgroundResource(R.drawable.ic_button_study);
                    break;
            }

            curr_num.setText(item.getCurrent_num().toString());
            max_num.setText(item.getMax_num().toString());

            name.setText(item.getName());
            content.setText(item.getContent());

            Glide.with(context)
                    .load(ClientApp.API_URL + item.getImageUri())
                    .into(image_group);
        }
    }
}