package com.doubleslash.playground.infoGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.R;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder>{
    ArrayList<Member> items = new ArrayList<Member>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.member_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Member item){
        items.add(item);
    }

    public void setItems(ArrayList<Member> items){
        this.items = items;
    }

    public Member getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView member_image;
        TextView name;
        RequestOptions option = new RequestOptions().circleCrop();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            member_image = itemView.findViewById(R.id.member_image);
            name = itemView.findViewById(R.id.name);
        }

        public void setItem(Member item){
            Glide.with(itemView.getContext())
                    .load(ClientApp.API_URL + item.getImage())
                    .apply(option)
                    .into(member_image);
            name.setText(item.getName());
        }
    }
}
