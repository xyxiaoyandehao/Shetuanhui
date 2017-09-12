package com.example.shetuanhui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ShetuanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shetuan);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        Button chuangjian=(Button) findViewById(R.id.chuangjian);
        Button jiaru=(Button) findViewById(R.id.jiaru);

        chuangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ShetuanActivity.this,ShetuanshenqingActivity.class);
                startActivity(intent);
            }
        });


    }
}
