package com.example.shetuanhui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

import static android.R.attr.resource;
import static android.R.attr.width;
import static com.example.shetuanhui.R.attr.height;

/**
 * Created by ASUS on 2017/9/7.
 */

public class ShetuanAdapter extends ArrayAdapter<Shetuan> {
    private int resourceId;
    public ShetuanAdapter(Context context, int textViewResourceId, List<Shetuan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Shetuan shetuan=getItem(position);
        View view;
        String touxiang=shetuan.getTouxiang().getUrl();
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder =new ViewHolder();
            viewHolder.shetuanImage = (ImageView)view.findViewById(R.id.shetuan_image);
            viewHolder.shetuanName = (TextView)view.findViewById(R.id.shetuan_name);
            view.setTag(viewHolder);
            Glide
                    .with(view)
                    .load(touxiang)
                    .into(viewHolder.shetuanImage);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
      // BmobFile icon=shetuan.getTouxiang();

        /*Glide
                .with(view)
                .load(touxiang)
                .into(viewHolder.shetuanImage);*/



        //  Bitmap bitmap = BitmapFactory.decodeFile(shetuan.getTouxiang().toString());
       // viewHolder.shetuanImage.setImageBitmap(getBitmap(touxiang));
       // viewHolder.shetuanImage.setImageResource(R.drawable.renzheng);
        viewHolder.shetuanName.setText(shetuan.getName());

        return view;
    }

    class ViewHolder{
        ImageView shetuanImage;
        TextView shetuanName;
    }

    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
}

