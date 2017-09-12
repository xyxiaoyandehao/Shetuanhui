package com.example.shetuanhui;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.ryane.banner_lib.AdPageInfo;
import com.ryane.banner_lib.AdPlayBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
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
}
