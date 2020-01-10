package com.example.recyclev;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //凡是需要使用的进行操作的数据(对象类型)不要忘记初始化否则会造成空引用
    private List<Fruit> flist = new ArrayList<>();

    private RecyclerView recyclerView;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniflist();
        recyclerView = findViewById(R.id.recycler_view);
//        通过LayoutManager来呈现出列表或者横向滑动列表或者是瀑布流的效果
//        横向或者竖向的列表布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,0,false);
//        瀑布流布局
        StaggeredGridLayoutManager layoutManager1=new StaggeredGridLayoutManager(3,1);
//          网格布局
        GridLayoutManager layoutManager2 = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(flist);
        recyclerView.setAdapter(adapter);
    }

    private void iniflist() {
        Data data = new Data();
        int length = data.imageList.size();
        for (int i = 0; i < length; i++) {
            flist.add(new Fruit(data.imageList.get(i), data.nameList.get(i)));
        }
    }
}
