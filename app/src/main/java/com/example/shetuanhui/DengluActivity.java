package com.example.shetuanhui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class DengluActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_denglu);

        Button zhuce=(Button)findViewById(R.id.zhuce);
        Button denglu=(Button)findViewById(R.id.denglu) ;
        final EditText zhanghao=(EditText)findViewById(R.id.zhanghao);
        final EditText mima=(EditText)findViewById(R.id.mima);
        Bmob.initialize(this, "6ebcbe3cc4ef865036e8a4e9d7260fff");
       // Bmob.initialize(this, "010d710a00131e54a940ca492f3ba104");

        BmobUser bmobUser = BmobUser.getCurrentUser();                  //读取用户信息缓存
        if(bmobUser != null){
            // 允许用户使用应用
            Intent intent =  new Intent(DengluActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DengluActivity.this,ZhuceActivity.class);
                startActivity(intent);
            }
        });

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String zhanghao2=zhanghao.getText().toString();
                String mima2=mima.getText().toString();

                User user = new User();
                user.setUsername(zhanghao2);
                user.setPassword(mima2);
                user.login(new SaveListener<User>() {

                    @Override
                    public void done(User user, BmobException e) {
                        if(e==null){
                            Toast.makeText(DengluActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent =  new Intent(DengluActivity.this,MainActivity.class);
                            startActivity(intent);
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                        }else{
                            Toast.makeText(DengluActivity.this,"账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
