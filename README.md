# BaseRecyclerViewAdapterHelper

![logo](https://vkceyugu.cdn.bspapp.com/VKCEYUGU-ced0c45b-e0db-473e-9a57-ba2cb6fc7e60/4775967a-fa82-47b9-a3dd-e8844a52aa87.jpg)  
一个简单的RecyclerView适配器的封装，欢迎使用。

## 导入方式
### 将JitPack存储库添加到您的构建文件中(项目根目录下build.gradle文件)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 添加依赖项
[![](https://jitpack.io/v/CymChad/BaseRecyclerViewAdapterHelper.svg)](https://jitpack.io/#CymChad/BaseRecyclerViewAdapterHelper)
仅支持`AndroidX`
```
dependencies {
    implementation 'com.github.Ywt0328:BaseRecyclerViewAdapter:1.0.2'
}
```

### 示例
#### 第一步创建适配器
```
public class DemoAdapter extends BaseSimpleAdapter<String, DemoAdapter.ViewHolder> {

    DemoAdapter(int resId, Context context) {
        super(resId, context);
    }

    @Override
    public void getMyView(ViewHolder holder, int position, String data) {
        holder.textView.setText(data);
    }

    class ViewHolder extends BaseSimpleViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
```
#### 第二步 recyclerView引用
```
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
```