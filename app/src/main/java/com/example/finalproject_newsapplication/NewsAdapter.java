package com.example.finalproject_newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private final Context mContext;
    private List<Article> articleData;

    public NewsAdapter(Context mContext, List<Article> articleData) {
        this.mContext = mContext;
        this.articleData = articleData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.news_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.newsTitle.setText(articleData.get(position).getTitle());
        holder.newDescription.setText(articleData.get(position).getContent());
        Glide.with(mContext).load(articleData.get(position).getUrlToImage()).into(holder.newsImage);


    }

    @Override
    public int getItemCount() {
        return articleData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView newsTitle,newDescription;
        ImageView newsImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newDescription = itemView.findViewById(R.id.newsContent);
            newsImage = itemView.findViewById(R.id.newsImage);

        }
    }
}
