package com.example.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSimpleAdapter<T,VH extends BaseSimpleViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> list;
    private int resId;
    Context context;
    private List<Integer> childIds = new ArrayList();
    private ItemChildClickListener itemChildClickListener;
    private ItemClickListener itemClickListener;

    public BaseSimpleAdapter(int resId, Context context) {
        this.resId = resId;
        this.context = context;
    }

    public void setItemChildClickListener(ItemChildClickListener itemChildClickListener) {
        this.itemChildClickListener = itemChildClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void addChildClickViewIds(int... arr){
        for(int e : arr){
            childIds.add(e);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId, parent, false);
        Class<VH> vhClass = getTClass();//反射 获取泛型实例
        try {
            //反射构造函数  https://www.jb51.net/article/233598.htm
            Constructor<VH> c = vhClass.getDeclaredConstructor(View.class);
            c.setAccessible(true);
            VH viewHolder = c.newInstance(view);
            bindViewClickListener(viewHolder);
            return viewHolder;//使用构造函数创建实例
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<VH> getTClass() {
        return (Class<VH>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        getMyView(holder,position,list.get(position));
    }

    public abstract void getMyView(VH holder, int position, T data);

    private void bindViewClickListener(VH viewHolder){
        if(itemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.click(viewHolder.itemView);
                }
            });
        }
        if(itemChildClickListener != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                childIds.forEach(item -> {
                    viewHolder.itemView.findViewById(item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemChildClickListener.click(v);
                        }
                    });
                });
            }
        }
    }

    public void setData(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ItemChildClickListener{
        void click(View view);
    }
    public interface ItemClickListener{
        void click(View view);
    }
}
