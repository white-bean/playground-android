package com.doubleslash.playground.GroupList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.R;
import com.doubleslash.playground.profile.MyGroup;

import java.util.ArrayList;

public class MyGroupAdapter2 extends RecyclerView.Adapter<MyGroupAdapter2.ViewHolder> implements OnMyGroupItem2ClickListener {
    ArrayList<MyGroup> items = new ArrayList<MyGroup>();
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
        MyGroup item = items.get(position);
        holder.setItem(context, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MyGroup item){
        items.add(item);
    }

    public void setItems(ArrayList<MyGroup> items){
        this.items = items;
    }

    public MyGroup getItem(int position){
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
        ImageView category;
        ConstraintLayout category_name;
        TextView tv_group_category;

        TextView name;
        TextView location;

        TextView curr_num;
        TextView max_num;

        public ViewHolder(View itemView, final OnMyGroupItem2ClickListener listener){
            super(itemView);

            category = itemView.findViewById(R.id.image_group_category); // 카테고리
            category_name = itemView.findViewById(R.id.layout_group_category);
            tv_group_category = itemView.findViewById(R.id.tv_group_category);

            name = itemView.findViewById(R.id.subject_tV);               // 그룹 제목
            location = itemView.findViewById(R.id.location_tV);          // 위치

            curr_num = itemView.findViewById(R.id.curr_tV);              // 현재 인원 수
            max_num = itemView.findViewById(R.id.total_tV);              // 전체 인원 수

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (listener != null){
                    listener.onItemClick(ViewHolder.this, view, position);
                }
            });
        }

        public void setItem(Context context, MyGroup item) {
            switch (item.getCategory()) {
                case "스터디":
                    tv_group_category.setText(item.getCategory());
                    category.setImageResource(R.drawable.writing_hand);
                    category_name.setBackgroundColor(Color.parseColor("#4ed2ae"));
                    break;
                case "운동/다이어트":
                    tv_group_category.setText(item.getCategory());
                    category.setImageResource(R.drawable.diet);
                    category_name.setBackgroundColor(Color.parseColor("#4ac3e8"));
                    break;
                case "문화생활":
                    tv_group_category.setText(item.getCategory());
                    category.setImageResource(R.drawable.draw);
                    category_name.setBackgroundColor(Color.parseColor("#ff9880"));
                    break;
                case "게임":
                    tv_group_category.setText(item.getCategory());
                    category.setImageResource(R.drawable.game);
                    category_name.setBackgroundColor(Color.parseColor("#ffc644"));
                    break;
                default:
                    tv_group_category.setText(item.getCategory());
                    category.setImageResource(R.drawable.ic_camera);
                    category_name.setBackgroundColor(Color.parseColor("#4ed2ae"));
                    break;
            }

            name.setText(item.getName());
            location.setText(item.getLocation());

            curr_num.setText(item.getCurrent_num().toString());
            max_num.setText(item.getMaximum_num().toString());
        }
    }
}