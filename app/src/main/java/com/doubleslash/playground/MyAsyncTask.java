package com.doubleslash.playground;

import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.chat.ChatActivity;
import com.doubleslash.playground.chat.ChatAdapter;

public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {

    RecyclerView recyclerView;
    ChatAdapter adapter;


    public MyAsyncTask(ChatAdapter adapter,RecyclerView recyclerView)
    {
        this.recyclerView = recyclerView;
        this.adapter=adapter;
    }

    @Override
    protected Boolean doInBackground(Void... strings){

        for(int i=0; i< 10000; i++)
        {
            publishProgress(i);
        }

        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean s) {
        super.onCancelled(s);
    }
}