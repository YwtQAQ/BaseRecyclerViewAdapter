package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    Mode mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(R.layout.item_news,this);
        recyclerView.setAdapter(newsAdapter);

        initListener();
        initData();

//        BaseQuickAdapter adapter;
//        adapter.addHeaderView();
    }

    private void initListener(){
        newsAdapter.addChildClickViewIds(R.id.imageView,R.id.textView);

        newsAdapter.setItemClickListener(new MyBaseAdapter.ItemClickListener() {
            @Override
            public void click(View view) {
                Toast.makeText(MainActivity.this, "item点击", Toast.LENGTH_SHORT).show();
            }
        });
        newsAdapter.setItemChildClickListener(new MyBaseAdapter.ItemChildClickListener() {
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
        newsAdapter.setData(list);
    }

    public void setMode(Mode mode){
        switch (mode){
            case create:
                Log.e("create","");
                break;
            case update:
                Log.e("update","");
                break;
        }
    }

    enum Mode{
        create,update
    }
}