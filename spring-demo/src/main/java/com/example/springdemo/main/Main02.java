package com.example.springdemo.main;

import com.example.springdemo.BeanPostProcessor.MyBeanPostProcessor;
import com.example.springdemo.pojo.User;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main02 {
    public static void main(String[] args) {
        //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        // 显示添加后置处理器
        bf.addBeanPostProcessor(bf.getBean(MyBeanPostProcessor.class));
        User user = bf.getBean(User.class);
        System.out.println(user);
    }
}
