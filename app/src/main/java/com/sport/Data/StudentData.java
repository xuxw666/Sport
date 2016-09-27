package com.sport.Data;

/**
 * Created by xxw on 2016/4/11.
 */
public class StudentData{
    private static String id;
    private static String name = "魏某某";
    private static String password;
    private static String telnumber = "135xxxxxxxx";
    private static String email = "weimoumou@163.com";


    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
