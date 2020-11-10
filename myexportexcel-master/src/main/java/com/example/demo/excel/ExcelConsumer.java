package com.example.demo.excel;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


public class ExcelConsumer<T> implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ExcelConsumer.class);

    //默认sheetSize
    private static Integer sheetSize = 100000;
    //数据类型
    private Class<T> clazz;
    //工作薄
    private SXSSFWorkbook wb;
    //sheetName
    private String sheetName = "";

    //数据适配器
    private ExportDataAdapter<T> exportDataAdapter;
    //线程同步
    private CountDownLatch latch;

    /**
     * 构造函数
     * @param clazz
     */
    public ExcelConsumer(Class<T> clazz, ExportDataAdapter<T>  exportDataAdapter, SXSSFWorkbook wb, CountDownLatch latch, String sheetName) {
        if(clazz == null || wb == null || exportDataAdapter == null || latch == null){
            logger.error("ExcelConsumer::初始化对象参数不能为空");
            return;
        }
        this.clazz = clazz;
        this.exportDataAdapter = exportDataAdapter;
        this.wb = wb;
        this.latch = latch;
        this.sheetName = sheetName == null ? "" : sheetName;
    }

    @Override
    public void run() {
        //初始化excel导出工具类
        ExcelUtil<T> excelUtil = new ExcelUtil<>(this.clazz);
        Sheet sheet = null;
        Integer sheetNo = 0;
        Integer rowNum = 1;
        T vo;
        //生产者还在生产数据
        while(latch.getCount() > 1){
            //生成sheetName
           if(rowNum == 1){
               sheetNo ++;
               sheet = excelUtil.createSheet(wb,sheetName.concat(sheetNo.toString()));
               excelUtil.setColumnTitle(sheet);
           }
           //获取数据
            vo = exportDataAdapter.getData();
           //往excel添加一行数据
            excelUtil.addRowData(vo,wb,sheet,rowNum);
            rowNum ++;
            //准备生成下一个sheetName
            if(rowNum == sheetSize + 1){
                rowNum = 1;
            }
       }
       //生产者不再生产数据，取剩余数据，并将数据写入excel
        Integer reminderDataSize = exportDataAdapter.getDataSize();
        T reminderData;
        if(reminderDataSize  > 0) {
            for (int i = 0; i < reminderDataSize; i++) {
                reminderData = exportDataAdapter.getData();
                if(rowNum == 1){
                    sheetNo ++;
                    sheet = excelUtil.createSheet(wb,sheetName.concat(sheetNo.toString()));
                    excelUtil.setColumnTitle(sheet);
                }
                excelUtil.addRowData(reminderData,wb,sheet,rowNum);
                rowNum ++;
                if(rowNum == sheetSize + 1){
                    rowNum = 1;
                }
            }
        }
        System.out.println("数据消费完成");
        latch.countDown();
    }
}
