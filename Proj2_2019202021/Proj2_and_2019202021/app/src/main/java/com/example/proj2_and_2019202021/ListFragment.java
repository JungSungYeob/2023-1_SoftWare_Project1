package com.example.proj2_and_2019202021;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import java.sql.Array;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private View view;

    private ListView listView;

    ArrayAdapter<String> adapter;

    String json_text; //json text 저장 변수

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);

        //json을 받아서 Array로 변환 후 각 key값의 Array에 add
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> items_id =new ArrayList<>();
        try{
            json_text = new Task(0).execute().get();
            JSONArray jsonArray = new JSONArray(json_text);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                items_id.add(jsonObject.getString("id"));
                items.add(jsonObject.getString("title"));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //외부 Adapter 말고 내부에서 사용
        adapter = new ArrayAdapter<String>(requireContext(),R.layout.list_item,R.id.listItemText,items){
          @Override
          public View getView(int position, View convertView, ViewGroup parent){
              View view = super.getView(position,convertView,parent);

              //parent의 높이 가져와서 비율 조정, 세로 6칸
              int screenHeight = listView.getHeight();
              int itemHeight = screenHeight/6;

              view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight));

              return view;
          }
        };
        //adapter 적용
        listView.setAdapter(adapter);
        //listView의 item 클릭시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemId = items_id.get(position); //position 별 id 가져오기

                Intent intent = new Intent(getActivity(),ArticleActivity.class); // ArticleActivity intent
                intent.putExtra("id",selectedItemId); //id 값을 intent의 extra 값으로 넘기기 (후에 정보 불러오기 위함)

                startActivity(intent);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}