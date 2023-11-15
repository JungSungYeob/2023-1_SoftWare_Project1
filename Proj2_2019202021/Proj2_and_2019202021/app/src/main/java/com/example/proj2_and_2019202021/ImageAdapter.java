package com.example.proj2_and_2019202021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> imageUrlList;


    //사진 주소에 대한 정보를 받아서 Gridview item ImageView에 적용하는 Adapter
    public ImageAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageUrlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        //부모의 높이 가져와서 비율 정하기(세로 4칸, 가로 3칸)
        int screenHeight = parent.getHeight();
        int screenWidth = parent.getWidth();
        int itemHeight = screenHeight/4;
        int itemWidth = screenWidth/3;


        ImageView imageView = convertView.findViewById(R.id.gridItemImage);
        String imageUrl = imageUrlList.get(position);


        //Picasso를 이용한 imageView에 이미지 적용
        Picasso.get().load(imageUrl).into(imageView);


        //비율 적용
        convertView.setLayoutParams(new AbsListView.LayoutParams(itemWidth,itemHeight));

        return convertView;
    }
}
