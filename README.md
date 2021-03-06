# RecyclerView + CardView  
Androidx下使用RecyclerVeiw和CardView实现列表（横向竖向）、网格、瀑布流  
RecyclerView比ListView更加容易使用，而且实现的效果也更加丰富  
**支持实现的效果：线性排列：分为横排列和竖排列；还支持瀑布流排列。**  

RecyclerView搭配CardView可以十分美观的满足你的各种需求。  
***
##### 添加RecyclerView依赖     
>implementation 'androidx.recyclerview:recyclerview:1.0.0'
##### 添加CardView依赖
> implementation 'androidx.cardview:cardview:1.0.0'  
***  

### RecyclerView  
![竖直排列](https://upload-images.jianshu.io/upload_images/19741117-95708453c0fbb4a7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![横向排列](https://upload-images.jianshu.io/upload_images/19741117-925b39142237319b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![瀑布流效果](https://upload-images.jianshu.io/upload_images/19741117-d805352d0eef20bd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![网格效果](https://upload-images.jianshu.io/upload_images/19741117-2c45d6e0bd8b7c15.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***  
以上三种布局方式都是基于layoutmanager来实现的。  
##### 实现步骤   
* 在mainActivity中初始化用来显示的数据集合List  
```
  private void iniflist() {
        Data data = new Data();
        int length = data.imageList.size();
        for (int i = 0; i < length; i++) {
            flist.add(new Fruit(data.imageList.get(i), data.nameList.get(i)));
        }
    }
```
* 书写RecyclerView对应的XML文件  
 ```
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_height="match_parent"
        android:layout_width="match_parent" />
```  

* 定义每一item的布局结合CardView可以将布局放在卡片内    
```
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <ImageView
        android:id="@+id/fruit_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left" />

    <TextView
        android:id="@+id/fruit_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/fruit_image"
        android:layout_marginTop="10dp" />
</RelativeLayout>
```

* 定义全局的RecyclerView并且findviewbyid  
* 在mainActivity中为recyclerView定义layoutManager和adapter  
```
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
```   
* 完成Adapter类的创建，并且在内部定义static类ViewHolder  
继承抽象类RecyclerView.Adapter<FruitAdapter.ViewHolder>
实现方法:  
ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)创建viewHolder完成监听注册    
onBindViewHolder(@NonNull ViewHolder holder, int position)将数据取出填充到布局    
getItemCount()返回数据项数     
内部类static class ViewHolder extends RecyclerView.ViewHolder  
```
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
```
***
### CardView  
设置卡片为圆角效果  
设置阴影有立体感  
设置点击呈现涟漪效果  
```
<!--    卡片布局效果将每一个item作为一个卡片-->
    <androidx.cardview.widget.CardView android:layout_height="wrap_content"
        android:id="@+id/f_view"
        android:layout_width="wrap_content"

        app:cardElevation="100dp"

        app:cardCornerRadius="4dp"

        android:clickable="true"
        android:foreground="?android:attr/selectableItemBackground"

        >

<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <ImageView
        android:id="@+id/fruit_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left" />

    <TextView
        android:id="@+id/fruit_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/fruit_image"
        android:layout_marginTop="10dp" />
</RelativeLayout>

</androidx.cardview.widget.CardView>
```
***
### 关键代码  
**Data.java(预加载初始化打包数据)**  
```

public class Data {
    public  List<Integer> imageList = new ArrayList<>();
    public   List<String> nameList = new ArrayList<>();


    public Data() {
        imageList.add(R.drawable.apple_pic);
        imageList.add(R.drawable.banana_pic);
        imageList.add(R.drawable.cherry_pic);
        imageList.add(R.drawable.grape_pic);
        imageList.add(R.drawable.mango_pic);
        imageList.add(R.drawable.orange_pic);
        imageList.add(R.drawable.pear_pic);
        imageList.add(R.drawable.pineapple_pic);
        imageList.add(R.drawable.strawberry_pic);
        imageList.add(R.drawable.watermelon_pic);
        nameList.add("Apple");
        nameList.add("Banana");
        nameList.add("Cherry");
        nameList.add("Grape");
        nameList.add("Mango");
        nameList.add("Orange");
        nameList.add("Pear");
        nameList.add("Pineapple");
        nameList.add("Strawberry");
        nameList.add("Watermelon");
        imageList.add(R.drawable.apple_pic);
        imageList.add(R.drawable.banana_pic);
        imageList.add(R.drawable.cherry_pic);
        imageList.add(R.drawable.grape_pic);
        imageList.add(R.drawable.mango_pic);
        imageList.add(R.drawable.orange_pic);
        imageList.add(R.drawable.pear_pic);
        imageList.add(R.drawable.pineapple_pic);
        imageList.add(R.drawable.strawberry_pic);
        imageList.add(R.drawable.watermelon_pic);
        nameList.add("Apple");
        nameList.add("Banana");
        nameList.add("Cherry");
        nameList.add("Grape");
        nameList.add("Mango");
        nameList.add("Orange");
        nameList.add("Pear");
        nameList.add("Pineapple");
        nameList.add("Strawberry");
        nameList.add("Watermelon");
    }

}
```   
**FruitAdapter.java**  
```

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
```  
**Main**    
```
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
```
