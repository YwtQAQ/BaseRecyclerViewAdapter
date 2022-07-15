package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.BaseSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        demoAdapter = new DemoAdapter(R.layout.item_news,this);
        recyclerView.setAdapter(demoAdapter);

        //设置点击事件
        initListener();
        //初始化数据
        initData();
    }

    private void initListener(){
        demoAdapter.addChildClickViewIds(R.id.imageView,R.id.textView);

        demoAdapter.setItemClickListener(new BaseSimpleAdapter.ItemClickListener() {
            @Override
            public void click(View view) {
                Toast.makeText(MainActivity.this, "item点击", Toast.LENGTH_SHORT).show();
            }
        });
        demoAdapter.setItemChildClickListener(new BaseSimpleAdapter.ItemChildClickListener() {
            @Override
            public void click(View view) {
                switch (view.getId()){
                    case R.id.imageView:
                        Log.e("image点击","111111");
                        Toast.makeText(MainActivity.this, "image点击", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.textView:
                        Toast.makeText(MainActivity.this, "textView点击", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initData(){
        List<String> list = new ArrayList();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        demoAdapter.setData(list);
    }
}