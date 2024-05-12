package com.example.finalproject_newsapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

public class DetailedNews extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news_layout);

        // Retrieve data passed from adapter
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            String url = intent.getStringExtra("image");

            // Set data to TextViews in detail layout
            TextView textViewTitle = findViewById(R.id.textViewTitle);
            TextView textViewContent = findViewById(R.id.textViewContent);
            ImageView imageView = findViewById(R.id.imageView);
            if (textViewTitle != null && textViewContent != null) {
                textViewTitle.setText(title != null ? title : "");
                textViewContent.setText(content != null ? content : "");
                FetchImageDetail fetchImageDetail = new FetchImageDetail(url, imageView);
                fetchImageDetail.start();
            }
        } else {
            // Handle case when intent is null
            Log.e("DetailedNews", "Intent is null");
            // Optionally finish this activity or show an error message
            finish();
        }
    }
}
class FetchImageDetail extends Thread {
    String url;
    Bitmap bitmap;
    ImageView imageView;

    FetchImageDetail(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        Log.d("Image","fetching image from "+ url);
        try {
            inputStream = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

