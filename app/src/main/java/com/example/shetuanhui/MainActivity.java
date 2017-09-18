package com.example.shetuanhui;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ryane.banner_lib.AdPageInfo;
import com.ryane.banner_lib.AdPlayBanner;



import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends Activity {
    public List<Shetuan> shetuanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);

        BmobQuery<Shetuan> query = new BmobQuery<Shetuan>();
//查询playerName叫“比目”的数据
        //query.addWhereEqualTo("playerName", "比目");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        //query.setLimit(50);
//执行查询方法

        query.findObjects(new FindListener<Shetuan>() {
            @Override
            public void done(List<Shetuan> object, BmobException e) {
                if(e==null){
                    for (Shetuan shetuan : object) {
                        shetuanList.add(shetuan);
                    }

                    ShetuanAdapter adapter = new ShetuanAdapter(MainActivity.this, R.layout.shetuan_item, shetuanList);
                    ListView listView = (ListView) findViewById(R.id.list_view);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){          //点击某个推荐社团
                        @Override
                        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
                            Shetuan shetuan = shetuanList.get(position);
                        }
                    });
                }else{
                }
            }
        });





        AdPlayBanner mAdPlayBanner = (AdPlayBanner) findViewById(R.id.game_banner);
        List<AdPageInfo> mDatas = new ArrayList<>();
        int[] image = {
                R.drawable.fengmian1,
                R.drawable.fengmian2,
                R.drawable.fengmian3
        };
        AdPageInfo info1 = new AdPageInfo("图片1", "http://bmob-cdn-13932.b0.upaiyun.com/2017/09/06/a189018940aa36ba803dabd33b66dbd6.jpg", "链接1", 3);
        AdPageInfo info2 = new AdPageInfo("图片2", "http://bmob-cdn-13932.b0.upaiyun.com/2017/09/06/c23c34ac40ace37980b4c4892d5a4a4c.jpg", "链接2", 2);
        AdPageInfo info3 = new AdPageInfo("图片3", "http://bmob-cdn-13932.b0.upaiyun.com/2017/09/06/7034c3b840b0154a809d27bf69054012.jpg", "链接3", 1);

        mDatas.add(info1);
        mDatas.add(info2);
        mDatas.add(info3);

        mAdPlayBanner.setInfoList((ArrayList<AdPageInfo>) mDatas);
        mAdPlayBanner.setUp();

        ImageButton shetuan=(ImageButton) findViewById(R.id.shetuan);
        ImageButton geren=(ImageButton) findViewById(R.id.geren);
        ImageButton liaotian=(ImageButton) findViewById(R.id.liaotian);
        ImageButton qita=(ImageButton) findViewById(R.id.qita);





        shetuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,ShetuanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initShetuan() {
           /* for (int i = 0; i < 3; i++) {
                Book book1 = new Book("一号书籍", R.drawable.yihao);
                bookList.add(book1);
                Book book2 = new Book("二号书籍", R.drawable.erhao);
                bookList.add(book2);
                Book book3 = new Book("三号书籍", R.drawable.sanhao);
                bookList.add(book3);
                Book book4 = new Book("三号书籍", R.drawable.sihao);
                bookList.add(book4);
                Book book5 = new Book("五号书籍", R.drawable.wuhao);
                bookList.add(book5);
            }*/




    }
}
