package com.example.springdemo.mybatis;


import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public class MetaObjectDemo {
    public static void main(String[] args) {
        Sessionkey sessionkey = new Sessionkey();
        sessionkey.setId(12345);
        sessionkey.setSessionkey("fdsafdsafd");
        sessionkey.setTboxId("lizhenjiang");
        MetaObject metaObject = SystemMetaObject.forObject(sessionkey);
        System.err.println(metaObject.getValue("id"));
    }
}
