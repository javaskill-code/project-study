package com.example.demo.excel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ExportDataAdapter<T> {


    //默认队列大小
    private  static Integer  DEFAULT_SIZE = 1000;

    private BlockingQueue<T> resourceQueue = null;

    //获取剩余数据数
    public Integer getDataSize(){
       return  resourceQueue.size();
    }

    //默认构造
    public ExportDataAdapter() {
        this.resourceQueue = new LinkedBlockingQueue<T>(DEFAULT_SIZE);
    }

    //添加数据到队列
    public void addData(T data){
        try {
            resourceQueue.put(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从队列中获取队列
    public T getData(){
        try {
            return  resourceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
