package com.example.jackson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";
    private static JacksonObject objectMapper=new JacksonObject(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<City> cityList=null;
        try {
            cityList= JSONUtil.deserializeList(getAssets().open("hot_city_list.txt"),City.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> deserializeList(InputStream inputStream , Class<T> clazz){
        List<T> list =new ArrayList<>();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            list = objectMapper.readValue(inputStream, javaType);
        } catch (JsonGenerationException e) {
            Log.d(TAG,"JsonGenerationException when deserialize json to object:"+e);
        } catch (JsonMappingException e) {
            Log.d(TAG,"JsonMappingException when deserialize json to object:"+e);
        } catch (IOException e) {
            Log.d(TAG,"IOException when deserialize json to object:"+e);
        }
        return list;
    }


}
