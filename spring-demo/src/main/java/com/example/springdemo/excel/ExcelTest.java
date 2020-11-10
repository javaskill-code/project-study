package com.example.springdemo.excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

public class ExcelTest {

    public static void main(String[] args) throws  Exception{
        ExcelTest excelTest = new ExcelTest();
        String excelPath = "d:\\test.xlsx";
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<UserVO> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(()->{
            excelTest.produceExportData(exportDataAdapter,latch);
        });
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(()->{
            new ExcelConsumer<UserVO>(UserVO.class,exportDataAdapter,sxssfWorkbook,latch,"测试记录").run();
        });
        latch.await();
        try {
            FileOutputStream outputStream = new FileOutputStream(excelPath);
            sxssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("写入Excel失败: ");
            e.printStackTrace();
        }
    }

    //模拟生产者生产数据（根据实际需求，比如分页从数据库取数据）
    public void produceExportData(ExportDataAdapter<UserVO> exportDataAdapter, CountDownLatch latch){
        for(int i=0;i<1000000;i++){
            UserVO vo = new UserVO(i,"姓名"+i,new BigDecimal(1200));
            exportDataAdapter.addData(vo);
        }
        System.out.println("数据生产完成");
        //数据生产结束
        latch.countDown();
    }


}
