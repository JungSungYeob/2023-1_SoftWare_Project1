package com.example.proj2_and_2019202021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ArticleActivity extends AppCompatActivity {

    public static Activity activity;

    private TextView title;
    private TextView contents;

    private String imageURL;

    private ImageView imageView;

    private Button backButton;
    private Button updateButton;
    private Button deleteButton;

    String json_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        activity = ArticleActivity.this; //다른 activity에서 종료하기 위한 전역변수

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        title = findViewById(R.id.title_article);
        contents =findViewById(R.id.content_article);
        imageView =findViewById(R.id.imageView_article);
        backButton = findViewById(R.id.backbutton_article);
        updateButton = findViewById(R.id.updateButton_article);
        deleteButton = findViewById(R.id.deleteButton_article);

        //id에 해당하는 json 받아오기
        try{
            json_text = new Task(Integer.parseInt(id)).execute().get();
            JSONObject jsonObject = new JSONObject(json_text);
            //각 요소 정보 업데이트
            title.setText(jsonObject.getString("title"));
            contents.setText(jsonObject.getString("contents"));
            imageURL = "http://10.0.2.2:8080/"+jsonObject.getString("filename");
            Picasso.get().load(imageURL).into(imageView);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        //수정 버튼
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ArticleActivity.this,UploadActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });
        //삭제 버튼
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBoard(id);
            }
        });


    }

    public void deleteBoard(String id){
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder().build();

        String url = "http://10.0.2.2:8080/ImageView/"+id;

        //해당 url에 post 보내기
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){//response 성공
                    Intent intent = new Intent(ArticleActivity.this,GalleryActivity.class);
                    GalleryActivity GA = (GalleryActivity) GalleryActivity.activity;
                    GA.finish();
                    //삭제 후 GalleryActivity로 이동
                    startActivity(intent);
                    finish();
                }else{//response 실패 그냥 뒤로가기
                    onBackPressed();
                    finish();
                }
            }
        });

    }
}