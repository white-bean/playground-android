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
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Type;

import java.util.ArrayList;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ViewHolder>{
    private ArrayList<Join> items = new ArrayList<Join>();
    private String category;
    private String teamId;

    public JoinAdapter(String category, String teamId) {
        this.category = category;
        this.teamId = teamId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.join_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Join item = items.get(position);
        holder.setItem(item, category, teamId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Join item){
        items.add(item);
    }

    public void setItems(ArrayList<Join> items){
        this.items = items;
    }

    public Join getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private RetrofitClient retrofitClient;
        private ImageView profileImage;
        private ImageView categoryImage;
        private TextView name;
        private TextView location;
        private TextView univ;
        private Button acceptBtn;
        private String teamId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);

            name = itemView.findViewById(R.id.user_name);
            location = itemView.findViewById(R.id.location);
            univ = itemView.findViewById(R.id.univ);
            acceptBtn = itemView.findViewById(R.id.accept_button);
            categoryImage = itemView.findViewById(R.id.category_image);
        }

        public void setItem(Join item, String category, String teamId){
            // profileImage.setImageResource(item.getImage());
            name.setText(item.getUserName());
            location.setText(item.getLocation());
            univ.setText(item.getUniv());

            retrofitClient = RetrofitClient.getInstance();

            acceptBtn.setOnClickListener(v -> {
                retrofitClient.group_request_accept(Aria.GROUP, Type.ACCEPT, Long.parseLong(item.getUserName()), teamId, System.currentTimeMillis());
            });

            switch (category) {
                case "스터디":
                    categoryImage.setBackgroundResource(R.drawable.ic_button_study);
                    break;
                case "운동/다이어트":
                    categoryImage.setBackgroundResource(R.drawable.ic_button_diet);
                    break;
                case "문화생활":
                    categoryImage.setBackgroundResource(R.drawable.ic_button_cultural);
                    break;
                case "게임":
                    categoryImage.setBackgroundResource(R.drawable.ic_button_game);
                    break;
                default:
                    categoryImage.setBackgroundResource(R.drawable.ic_button_study);
                    break;
            }
        }
    }
}
