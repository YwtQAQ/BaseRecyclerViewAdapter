package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
