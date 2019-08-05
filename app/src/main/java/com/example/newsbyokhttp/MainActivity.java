package com.example.newsbyokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
private TextView tvNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNews=findViewById(R.id.tvNews);

        OkHttpClient client=new OkHttpClient();

        String url="https://jsonplaceholder.typicode.com/posts";

        Request request=new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myresponse=response.body().string();


                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvNews.setText(myresponse);
                        }
                    });

                }
                else tvNews.setText("hello ji");




            }
        });

    }
}
