package com.example.shetuanhui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class ShetuanshenqingActivity extends Activity {
    Bitmap bitmap;
    String path;
    Uri imageUri;
    int  CHOOSE_PHOTO=10;
    int TAKE_PHOTO=5;
    public final int TYPE_TAKE_PHOTO = 1;//Uri获取类型判断
    public final int CODE_TAKE_PHOTO = 1;//相机RequestCode
    public final int CODE_SELECT_IMAGE = 2;//相册RequestCode
    Uri photoUri;
    File touxiangx;
    private static final int REQUEST_CODE_CHOOSE = 23;
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


        zhaoxiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoUri = getMediaFileUri(TYPE_TAKE_PHOTO);
                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
            }
        });

        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                /* 开启Pictures画面Type设定为image */
//                intent.setType("image/*");
//                /* 使用Intent.ACTION_GET_CONTENT这个Action */
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                /* 取得相片后返回本画面 */
//                startActivityForResult(intent, 1);
                Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(albumIntent, CODE_SELECT_IMAGE);
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
                    final Shetuan shetuan = new Shetuan();
                    /*String picPath = "src/res/drawable/qita.png";
                    shetuan.setTouxiang(new BmobFile(new File(picPath)));*/
                    shetuan.setName(mingcheng2);
                    shetuan.setJianjie(jianjie2);
                    User user = BmobUser.getCurrentUser(User.class);
                    shetuan.setUser(user);                                  //关联创始人为社长

                    //String path = touxiangx.getPath();                      //上传头像
                    //BmobFile bmobfile = new BmobFile(new File(path));
                    //final BmobFile bmobfile = new BmobFile("touxiang",null,new File(path).toString());
                    //shetuan.setTouxiang(bmobfile);

                    BmobRelation relation = new BmobRelation();             //多对多关联社员
                    relation.add(user);
                    shetuan.setSheyuan(relation);
                    final BmobFile bmobFile = new BmobFile(touxiangx);
                    //final BmobFile bmobFile = new BmobFile(new File(path));
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                shetuan.setTouxiang(bmobFile);
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
                                Toast.makeText(ShetuanshenqingActivity.this, "上传文件成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ShetuanshenqingActivity.this, "上传文件失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onProgress(Integer value) {
                            // 返回的上传进度（百分比）
                        }
                    });

                    /*shetuan.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(ShetuanshenqingActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ShetuanshenqingActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                }
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView touxiang=(ImageView) findViewById(R.id.touxiang);
        switch (requestCode) {
            case CODE_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    selectPic(data,touxiang);
                }
            case CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Log.i("URI", "data is not null");
                            Bitmap bitmap = data.getParcelableExtra("data");
                            touxiang.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换

                        }
                    } else {
                        Log.i("URI", "Data is null");

                        Bitmap bitmap = BitmapFactory.decodeFile( photoUri.getPath());
                        touxiang.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换

                    }
                }
                break;
        }
    }
    //选择照片
    private void selectPic(Intent intent,ImageView touxiang) {
        Uri selectImageUri = intent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        touxiang.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        touxiangx= new File(picturePath);
    }

    public Uri getMediaFileUri(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        touxiangx=mediaFile;
        return Uri.fromFile(mediaFile);
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
