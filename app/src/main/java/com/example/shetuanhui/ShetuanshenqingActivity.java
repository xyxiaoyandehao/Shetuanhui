package com.example.shetuanhui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ShetuanshenqingActivity extends AppCompatActivity {
    Bitmap bitmap;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shetuanshenqing);

        final EditText mingcheng=(EditText) findViewById(R.id.mingcheng);
        final EditText jianjie=(EditText) findViewById(R.id.jianjie);
        Button shenqing=(Button) findViewById(R.id.shenqing);
        Button fanhui=(Button) findViewById(R.id.fanhui);
        ImageButton zhaoxiangji=(ImageButton) findViewById(R.id.zhaoxiangji);
        ImageButton xiangce=(ImageButton) findViewById(R.id.xiangce);

        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });

        shenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mingcheng2 = mingcheng.getText().toString();
                String jianjie2 = jianjie.getText().toString();
                if (mingcheng2.equals("") || jianjie2.equals("")) {
                    Toast.makeText(ShetuanshenqingActivity.this, "社团名称和简介不得为空", Toast.LENGTH_SHORT).show();
                }else{
                    Shetuan shetuan = new Shetuan();
                    shetuan.setName(mingcheng2);
                    shetuan.setJianjie(jianjie2);
                    User user = BmobUser.getCurrentUser(User.class);
                    shetuan.setUser(user);                                  //关联创始人为社长

                    BmobRelation relation = new BmobRelation();             //多对多关联社员
                    relation.add(user);
                    shetuan.setSheyuan(relation);
                    shetuan.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(ShetuanshenqingActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ShetuanshenqingActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


            }
        }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView touxiang = (ImageView) findViewById(R.id.touxiang);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            path = getImagePath(uri, null);
            ContentResolver cr = this.getContentResolver();
            try {
                Log.e("qwe", path.toString());
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                /* 将Bitmap设定到ImageView */
                touxiang.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("qwe", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();

        }
        return path;

    }

    public static File saveFile(Bitmap bm,String path, String fileName) throws IOException {        //Bitmap转文件
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path , fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }
}
