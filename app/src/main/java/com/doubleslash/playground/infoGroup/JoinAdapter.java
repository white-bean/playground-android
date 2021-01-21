package com.doubleslash.playground.infoGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.R;

import java.util.ArrayList;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ViewHolder> implements OnJoinItemClickListener {
    private ArrayList<Join> items = new ArrayList<>();
    OnJoinItemClickListener listener;

    Context context;

    JoinAdapter(Context context) {
        this.context = context;
    }

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

        if (position == 0) {
            holder.userName.setText("김동현");
            Glide.with(context)
                    .load("https://img.hankyung.com/photo/201910/2019101010155619091-540x810.jpg")
                    .into(holder.profileImage);
        } else if (position == 1) {
            holder.userName.setText("한기찬");
            Glide.with(context)
                    .load("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA3MzBfMTg4%2FMDAxNTk2MDcwMjM5NDYy.Jk9jqK7VubzxpCZG-c0U9zn_L_nQgrNXLLaL34BPW-Eg.4fVYlawl2cUhYQVxKwGkkr9FTQyLsE3LJ_M-vaXuji0g.JPEG.ssangcopi335%2F%25A4%25A7.JPG&type=sc960_832")
                    .into(holder.profileImage);
        } else if (position == 2) {
            holder.userName.setText("유병재");
            Glide.with(context)
                    .load("data:image/jpeg;base64,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")
                    .into(holder.profileImage);
        }

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
