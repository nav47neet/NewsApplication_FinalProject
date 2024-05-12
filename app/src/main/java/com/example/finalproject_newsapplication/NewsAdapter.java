package com.example.finalproject_newsapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Handler;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {

        holder.newsTitle.setText(articleData.get(position).getTitle());
        holder.newDescription.setText(articleData.get(position).getDescription());
        String urlToImage = articleData.get(position).getUrlToImage();
        new FetchImage(urlToImage,holder).start();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailedActivity(position);
            }
        });

    }

    private void openDetailedActivity(int position) {
        Article clickedArticle = articleData.get(position);
        Intent intent = new Intent(mContext,DetailedNews.class);
        intent.putExtra("title",clickedArticle.getTitle());
        intent.putExtra("content",clickedArticle.getContent());
        intent.putExtra("image",clickedArticle.getUrlToImage());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
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
class FetchImage extends Thread{
    String url;
    Bitmap bitmap;
    NewsAdapter.MyViewHolder holder;
    FetchImage(String url, NewsAdapter.MyViewHolder holder){
        this.url =  url;
        this.holder = holder;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            holder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    if(bitmap!=null){
                        holder.newsImage.setImageBitmap(bitmap);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}