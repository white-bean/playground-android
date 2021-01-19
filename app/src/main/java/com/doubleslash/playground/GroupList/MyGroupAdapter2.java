package com.doubleslash.playground.GroupList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.R;

import java.util.ArrayList;

public class MyGroupAdapter2 extends RecyclerView.Adapter<MyGroupAdapter2.ViewHolder> implements OnMyGroupItem2ClickListener {
    ArrayList<MyGroup2> items = new ArrayList<MyGroup2>();
    OnMyGroupItem2ClickListener listener;

    private Context context;

    public MyGroupAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.my_group_item2, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyGroup2 item = items.get(position);
        holder.setItem(context, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MyGroup2 item){
        items.add(item);
    }

    public void setItems(ArrayList<MyGroup2> items){
        this.items = items;
    }

    public MyGroup2 getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnMyGroupItem2ClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView location;
        ImageView category;
        TextView curr_num;
        TextView max_num;
        TextView name;
        TextView content;
        ImageView image_group;

        public ViewHolder(View itemView, final OnMyGroupItem2ClickListener listener){
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

        public void setItem(Context context, MyGroup2 item) {
            location.setText(item.getLocation());

            switch (item.getCategory()) {
                case "스터디":
                    category.setImageResource(R.drawable.writing_hand);
                    break;
                case "운동/다이어트":
                    category.setImageResource(R.drawable.diet);
                    break;
                case "문화생활":
                    category.setImageResource(R.drawable.draw);
                    break;
                case "게임":
                    category.setImageResource(R.drawable.game);
                    break;
            }

            curr_num.setText(item.getCurrent_num());
            max_num.setText(item.getMax_num());

            name.setText(item.getName());
            content.setText(item.getContent());

            Glide.with(context)
                    .load(item.getImageUri())
                    .into(image_group);
        }
    }
}
