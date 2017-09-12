package com.example.shetuanhui;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by ASUS on 2017/9/10.
 */

public class User extends BmobUser {
    private int id;
    private String qianming;
    private BmobFile touxiang;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getQianming(){
        return this.qianming;
    }
    public void setQianming(String qianming){
        this.qianming=qianming;
    }
    public BmobFile getTouxiang(){
        return this.touxiang;
    }
    public void setTouxiang(BmobFile touxiang){
        this.touxiang=touxiang;
    }

}
