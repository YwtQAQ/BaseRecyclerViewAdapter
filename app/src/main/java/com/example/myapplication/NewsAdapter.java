package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class NewsAdapter extends MyBaseAdapter<String, NewsAdapter.ViewHolder>{

    NewsAdapter(int resId, Context context) {
        super(resId, context);
    }

    @Override
    public void getMyView(ViewHolder holder, int position, String data) {
        holder.textView.setText(data);
    }

    class ViewHolder extends MyBaseViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
