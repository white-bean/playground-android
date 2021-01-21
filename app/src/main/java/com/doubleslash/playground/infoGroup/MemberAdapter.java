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

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> implements OnMemberItemClickListener{
    ArrayList<Member> items = new ArrayList<>();
    OnMemberItemClickListener listener;

    private long kingId;

    public MemberAdapter(long kingId) {
        this.kingId = kingId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.member_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member item = items.get(position);
        holder.setItem(item, kingId);
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

    public void setOnItemClickListener(OnMemberItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_group_king;

        ImageView member_image;
        TextView name;

        RequestOptions option = new RequestOptions().circleCrop();

        public ViewHolder(@NonNull View itemView, final OnMemberItemClickListener listener) {
            super(itemView);

            image_group_king = itemView.findViewById(R.id.image_group_king);

            member_image = itemView.findViewById(R.id.member_image);
            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (listener != null){
                    listener.onItemClick(this, view, position);
                }
            });
        }

        public void setItem(Member item, long kingId){
            if (item.getId() == kingId) {
                image_group_king.setVisibility(View.VISIBLE);
            }

            Glide.with(itemView.getContext())
                    .load(ClientApp.API_URL + item.getImage())
                    .apply(option)
                    .into(member_image);

            name.setText(item.getName());
        }
    }
}
