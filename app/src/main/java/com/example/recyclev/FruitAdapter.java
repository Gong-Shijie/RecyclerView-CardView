package com.example.recyclev;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> flist;

    public FruitAdapter(List<Fruit> flist) {
        this.flist = flist;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//完成创建ViewHolder和将布局用布局加载器加载到viewHolder中
//为viewHolder里面的子view注册监听事件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewHolder.getAdapterPosition()将会返回被点击的item的index
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = flist.get(position);
                Toast.makeText(v.getContext(),"Click:"+fruit.getF_name(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = flist.get(position);
                Toast.makeText(v.getContext(),"Click: Image",Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //根据position将每一个flist里面的值绑定到viewholder
Fruit fruit = flist.get(position);
holder.imageView.setImageResource(fruit.getF_image());
holder.textView.setText(fruit.getF_name());
    }

    @Override
    public int getItemCount() {
        //返回flist的长度
        return flist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        //这些控件都可以设置监听事件
View fView;
ImageView imageView;
TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fView = itemView.findViewById(R.id.f_view);
            imageView = itemView.findViewById(R.id.fruit_image);
            textView = itemView.findViewById(R.id.fruit_name);
        }
    }

}
