package com.example.proj2_and_2019202021;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class ImageFragment extends Fragment {

    private View view;
    private GridView gridView;

    String json_text; //get json_text
    ArrayAdapter<String> adapter;

    //json에서 받은 item(이미지 주소)과 id
    ArrayList<String> items;
    ArrayList<String> items_id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);
        gridView = view.findViewById(R.id.gridView);

        //json을 받아서 Array로 변환 후 각 key값의 Array에 add
        items = new ArrayList<>();
        items_id = new ArrayList<>();
        try {
            json_text = new Task(0).execute().get();
            JSONArray jsonArray = new JSONArray(json_text);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                items_id.add(jsonObject.getString("id"));
                items.add("http://10.0.2.2:8080/"+jsonObject.getString("filename"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //imageAdapter 적용
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(),items);
        gridView.setAdapter(imageAdapter);
        //gridView item 클릭 시
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemId = items_id.get(position); //id 가져오기

                Intent intent = new Intent(getActivity(),ArticleActivity.class);//ArticleActivity intent
                intent.putExtra("id",selectedItemId);//id 값을 intent의 extra 값으로 넘기기 (후에 정보 불러오기 위함)

                startActivity(intent);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}