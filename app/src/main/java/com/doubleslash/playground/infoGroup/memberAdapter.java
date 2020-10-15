package com.doubleslash.playground.infoGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.GroupList.Group;
import com.doubleslash.playground.R;

import java.util.ArrayList;

public class memberAdapter extends RecyclerView.Adapter<memberAdapter.ViewHolder>{
    ArrayList<member> items = new ArrayList<member>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.member_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        member item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(member item){
        items.add(item);
    }

    public void setItems(ArrayList<member> items){
        this.items = items;
    }

    public member getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView member_image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            member_image = itemView.findViewById(R.id.member_image);
            name = itemView.findViewById(R.id.name);
        }

        public void setItem(member item){
            member_image.setImageResource(item.getImage());
            name.setText(item.getName());
        }
    }
}
