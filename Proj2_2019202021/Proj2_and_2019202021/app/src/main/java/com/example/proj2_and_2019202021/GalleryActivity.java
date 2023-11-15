package com.example.proj2_and_2019202021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//3개의 fragment를 다루는 Activity
public class GalleryActivity extends AppCompatActivity {

    public static Activity activity;

    private BottomNavigationView mBottomNV; //BottomNavigation View
    public Button uploadButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        activity = GalleryActivity.this; //다른 activity에서 종료하기 위한 전역변수

        //add 버튼 누르면
        uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(GalleryActivity.this, UploadActivity.class); //intent
                startActivity(intent);
            }
        });

        //각 item들이 눌러지면 해당하는 fragment 호출.
        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        //처음 fragment
        mBottomNV.setSelectedItemId(R.id.navigation_1);
    }

    private void BottomNavigate(int id){
        //id 변환
        String tag = String.valueOf(id);
        //fragmentmanager 가져오기
        FragmentManager fragmentManager = getSupportFragmentManager();
        //객체 생성하여 트랜잭션 시작
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        //현재 표시 중인 Fragment 가져오기
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        //만약 fragment 있으면 숨기기
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        //tag로 fragment 찾기
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new ImageFragment();

            } else if (id == R.id.navigation_2){

                fragment = new ListFragment();
            }else {
                fragment = new SettingFragment();
            }

            //framelayout에 트랜젝션 추가
            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            //있으면 show
            fragmentTransaction.show(fragment);
        }
        //트랜젝션의 주요 프로그먼트 설정
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        //프레그먼트 순서 변경 허용
        fragmentTransaction.setReorderingAllowed(true);
        //변경 내용 즉시 적용
        fragmentTransaction.commitNow();
    }
}