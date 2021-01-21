package com.doubleslash.playground.infoGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.R;

import java.util.ArrayList;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ViewHolder> implements OnJoinItemClickListener {
    private ArrayList<Join> items = new ArrayList<>();
    OnJoinItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.join_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Join item = items.get(position);

        // profileImage.setImageResource(item.getImage());

        holder.university.setText(item.getUserName());
        holder.location.setText(item.getLocation());
        holder.university.setText(item.getUniversity());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Join item){
        items.add(item);
    }

    public void removeItem(int position) {
        items.remove(position);

        notifyDataSetChanged();
    }

    public void setItems(ArrayList<Join> items){
        this.items = items;
    }

    public Join getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnJoinItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView profileImage;
        private ImageView categoryImage;

        private TextView userName;
        private TextView location;
        private TextView university;

        private Button acceptBtn;

        public ViewHolder(@NonNull View itemView, OnJoinItemClickListener listener) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            categoryImage = itemView.findViewById(R.id.category_image);

            userName = itemView.findViewById(R.id.user_name);
            location = itemView.findViewById(R.id.location);
            university = itemView.findViewById(R.id.university);

            acceptBtn = itemView.findViewById(R.id.accept_button);

            acceptBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (listener != null){
                    listener.onItemClick(this, view, position);
                }
            });
        }
    }
}
