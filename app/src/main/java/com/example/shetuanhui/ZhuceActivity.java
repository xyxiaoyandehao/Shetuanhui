package com.example.shetuanhui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class ZhuceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        final EditText zhanghao=(EditText) findViewById(R.id.zhanghao);
        final EditText mima=(EditText) findViewById(R.id.mima);
        final ImageButton fanhui=(ImageButton) findViewById(R.id.fanhui);
        Button next=(Button) findViewById(R.id.next);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ZhuceActivity.this,DengluActivity.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhanghao2 = zhanghao.getText().toString();
                String mima2 = mima.getText().toString();
                if(zhanghao2.length()<=6){
                    Toast.makeText(ZhuceActivity.this,"账号至少在7位以上", Toast.LENGTH_SHORT).show();
                }else{
                    if(mima2.length()<=6){
                        Toast.makeText(ZhuceActivity.this,"密码至少在7位以上", Toast.LENGTH_SHORT).show();
                    }else{

                        User user = new User();
                        user.setUsername(zhanghao2);
                        user.setPassword(mima2);

                        user.signUp(new SaveListener<User>() {
                            @Override
                            public void done(User s, BmobException e) {
                                if(e==null){
                                    Toast.makeText(ZhuceActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                                    Intent intent =  new Intent(ZhuceActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(ZhuceActivity.this,"注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            }
        });
    }
}
