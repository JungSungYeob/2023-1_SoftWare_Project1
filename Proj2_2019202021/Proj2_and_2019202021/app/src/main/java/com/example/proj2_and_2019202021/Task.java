package com.example.proj2_and_2019202021;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {

    private int pagenum;

    String clientKey = "#########################";;
    private String str, receiveMsg;
    private final String ID = "########";

    @Override
    protected String doInBackground(String... params) {
        //base url
        String url_base = "http://10.0.2.2:8080/Image/";

        URL url = null;
        try {
            if(pagenum==0){
                url = new URL(url_base);//0이면 전체 json을 받을 수 있도록 설정함
            }else {
                url = new URL(url_base + Integer.toString(pagenum)); // 서버 URL
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);
            //HTTP_OK를 받으면
            if (conn.getResponseCode() == conn.HTTP_OK) {
                //json 읽어서 buffer 저장
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                //receiveMsg 저장
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("결과", conn.getResponseCode() + "Error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }

    public Task(int number) {
        this.pagenum = number;
    }
}