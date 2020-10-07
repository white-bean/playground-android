package com.doubleslash.playground.GroupList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.R;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> implements OnGroupItemClickListener{
    ArrayList<Group> items = new ArrayList<Group>();
    OnGroupItemClickListener listener;

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
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Group item){
        items.add(item);
    }

    public void setItems(ArrayList<Group> items){
        this.items = items;
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


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView location;
        TextView category;
        TextView curr_num;
        TextView total_num;
        TextView subject;
        TextView information;
        ImageView picture;

        public ViewHolder(View itemView, final OnGroupItemClickListener listener){
            super(itemView);

            location = itemView.findViewById(R.id.location_tV); //위치
            category = itemView.findViewById(R.id.category_tV); //카테고리
            curr_num = itemView.findViewById(R.id.curr_tV);     //현재 인원 수
            total_num = itemView.findViewById(R.id.total_tV);   //전체 인원 수
            subject = itemView.findViewById(R.id.subject_tV);   // 그룹 제목
            information = itemView.findViewById(R.id.info_tV);  // 그룹 소개
            picture = itemView.findViewById(R.id.group_pic); // 그룹 사진

            itemView.setOnClickListener(new View.OnClickListener(){ // 클릭 이벤트
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                    //클릭 시 동작
                }
            });
        }
        public void setItem(Group item){
            location.setText(item.getLocation());
            category.setText(item.getCategory());
            curr_num.setText(item.getCurrent_num());
            total_num.setText(item.getTotal_num());
            subject.setText(item.getSubject());
            information.setText(item.getInformation());
            picture.setImageResource(item.getPicture());
        }
    }
}
