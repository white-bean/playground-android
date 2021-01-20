package com.doubleslash.playground.register;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doubleslash.playground.R;

import java.util.List;

public class Search_school_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private String search_str;
    public Search_school_Adapter(List<String> list, Context context, String search_str){

        this.search_str=search_str;
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.school_listview,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.

        viewHolder.label.setTextColor(Color.parseColor("#b9bcce"));
        SpannableString spannableString=new SpannableString(list.get(position));
        int start = list.get(position).indexOf(search_str);
        int end = start + search_str.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#333d4b")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.label.setText(spannableString);

        return convertView;
    }

    class ViewHolder{
        public TextView label;
    }
}
