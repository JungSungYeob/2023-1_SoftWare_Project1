package com.example.proj2_and_2019202021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UploadActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK=1;

    private static final String SERVER_URL = "http://10.0.2.2:8080/board";
    private static final int PERMISSION_REQUEST_CODE = 100;
    //Permission List
    private static String[] PERMISSIONS_STORAGE={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES
    };
    //파일 접근 권한을 요청하는 함수, API 34에서는 READ_MEDIA_IMAGES만 유효
    public static void verifyStoragePermissions(Activity activity){
        //permission 있는지 검사
        int writePermission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE);
        int managePermission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        int readImgPermission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_MEDIA_IMAGES);
        //없으면 permission 요청
        if(writePermission!=PackageManager.PERMISSION_GRANTED
                ||readPermission!=PackageManager.PERMISSION_GRANTED
                ||managePermission!=PackageManager.PERMISSION_GRANTED
                ||readImgPermission!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE,PERMISSION_REQUEST_CODE);
        }

    }

    private Button backButton;
    private Button imageSelectButton;
    private EditText etTitle;
    private EditText etContents;
    private Button uploadButton;
    private ImageView imageView;
    private Uri selectedImageUri;
    String json_text;
    private String imageURL;
    String id;


    //HTTP POST or PUT 통신 함수, upload를 먼저 구현하여 이름이 postBoard이다.
    public void postBoard(String title, String contents, Uri uri){
        //client 지정
        OkHttpClient client = new OkHttpClient();
        //이미지 타입 지정
        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");

        //파일 경로를 가져오기 위한 코드
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection,null,null,null);
        int columnindex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnindex);
        cursor.close();
        //이미지파일 자체 가져오기
        File imageFile = new File(filePath);
        //업로드 or 수정을 위한 Multipartbody
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title",title)
                .addFormDataPart("contents",contents)
                .addFormDataPart("file",imageFile.getName(),RequestBody.create(MEDIA_TYPE_IMAGE,imageFile));
        //requestbody 빌드
        RequestBody requestBody = requestBodyBuilder.build();

        Request request;
        if(id==null){//만약 upload
            request = new Request.Builder()
                    .url(SERVER_URL)
                    .post(requestBody)//Post 하기
                    .build();
        }else{//만약 update
            request = new Request.Builder()
                    .url(SERVER_URL+"/"+id)
                    .put(requestBody) //Put하기
                    .build();
        }

        Call call = client.newCall(request);
        call.enqueue(new Callback() { //요청 보내기
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){ //Response가 정상적으로 왔다면
                    String responseBody = response.body().string();
                    Intent intent = new Intent(UploadActivity.this, GalleryActivity.class);
                    //모든 Activity 종료 후 GalleryActivity 로 이동
                    GalleryActivity GA = (GalleryActivity) GalleryActivity.activity;
                    GA.finish();
                    if(id!=null){
                    ArticleActivity AA = (ArticleActivity)ArticleActivity.activity;
                    AA.finish();}
                    startActivity(intent);
                    finish();
                }else{//실패했다면 그냥 뒤로 돌아가기
                    onBackPressed();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();
        id = intent.getStringExtra("id"); //extraString 가져오기


        //화면 시작 시 permission 받기
        verifyStoragePermissions(this);

        etTitle = findViewById(R.id.uploadTitle);
        etContents = findViewById(R.id.uploadContents);
        uploadButton = findViewById(R.id.uploadCompleteButton);
        imageView = findViewById(R.id.image_view);
        backButton = findViewById(R.id.backbutton);

        //만약 upload가 아닌 update이면
        if(id!=null){
            uploadButton.setText("수정");
            try{
                json_text = new Task(Integer.parseInt(id)).execute().get();
                JSONObject jsonObject = new JSONObject(json_text);
                etTitle.setText(jsonObject.getString("title"));
                etContents.setText(jsonObject.getString("contents"));
                imageURL = "http://10.0.2.2:8080/"+jsonObject.getString("filename");
                Picasso.get().load(imageURL).into(imageView);
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        //업로드 버튼
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String contents = etContents.getText().toString();
                if(selectedImageUri ==null){
                    Toast.makeText(getApplicationContext(),"사진을 선택하세요",Toast.LENGTH_SHORT).show();
                }else {
                    postBoard(title, contents, selectedImageUri);
                }
            }
        });
        //image select button 누르면
        imageSelectButton = findViewById(R.id.imageSelectButton);
        imageSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //File을 고를 수 있는 ACTION_PICK, MediaStore.Images.으로 이동
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_IMAGE_PICK);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //사진 고르고 난 후
        super.onActivityResult(resultCode,resultCode,data);
        if(requestCode==REQUEST_IMAGE_PICK&&resultCode==RESULT_OK&&data!=null){
            selectedImageUri = data.getData();
            try{
                //제안서에서는 imageview에 대한 언급이 없지만 html에서 할 때에 비해 사진을 정상적으로 가져왔는지 확인이 어려워서 만듦
                imageView.setImageURI(selectedImageUri);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}