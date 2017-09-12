package com.example.shetuanhui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder =new ViewHolder();
            viewHolder.bookImage = (ImageView)view.findViewById(R.id.shetuan_image);
            viewHolder.bookName = (TextView)view.findViewById(R.id.shetuan_name);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
       // Bitmap bitmap = BitmapFactory.decodeFile(shetuan.getTouxiang());
      //  viewHolder.bookImage.setImageResource(shetuan.getTouxiang());
        viewHolder.bookName.setText(shetuan.getName());

        return view;
    }

    class ViewHolder{
        ImageView bookImage;
        TextView bookName;
    }
}

