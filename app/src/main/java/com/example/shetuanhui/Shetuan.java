package com.example.shetuanhui;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by ASUS on 2017/9/7.
 */

public class Shetuan extends BmobObject  {
    private int id;
    private String name="未知";
    private String jianjie;
    //String picPath = "src/res/drawable/qita.png";
    BmobFile bmobFile;
    private BmobFile touxiang=bmobFile;
    private String gonggao;
    private User user;
    private BmobRelation sheyuan;

    Shetuan(String name){
        this.name=name;
    }
    Shetuan(){
        this.name=name;
    }
    public BmobRelation getSheyuan(){return sheyuan;}
    public void setSheyuan(BmobRelation sheyuan){
        this.sheyuan=sheyuan;
    }

    public User getUser(){return user;}
    public void setUser(User user){
        this.user=user;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getJianjie(){
        return jianjie;
    }
    public void setJianjie(String jianjie){
        this.jianjie=jianjie;
    }

    public BmobFile getTouxiang(){
        return touxiang;
    }
    public void setTouxiang(BmobFile touxiang){
        this.touxiang=touxiang;
    }
    public String getGonggao(){
        return gonggao;
    }
    public void setGonggao(String gonggao){
        this.gonggao=gonggao;
    }
}
