package com.sport.Data;

/**
 * Created by xxw on 2016/4/22.
 */
public class ChatData {
    private static int image = 0;
    private static String name = "xxw";
    private static String desc = "666";

//    public ChatData




//    public void setDefault(int image,String name,String desc){
//        this.image = image;
//        this.name  = name;
//        this.desc = desc;
//    }

    public static int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
