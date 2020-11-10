package com.example.springdemo.excel;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelUtil<T> {

    Class<T> clazz;

    //表头字段列表
    private List<Field> fields;

    private CellStyle decimalCellStyle = null;

    /**
     * 构造函数
     *
     * @param clazz
     */
    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    //添加一条数据
    public void addRowData(T vo, SXSSFWorkbook wb, Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum);//产生一行
        Field field;
        Cell cell ;
        ExcelVOAttribute attr;
        int fieldSize = fields.size();
        for (int j = 0; j < fieldSize; j++) {
            field = fields.get(j);// 获得field.
            field.setAccessible(true);// 设置实体类私有属性可访问
            attr = field.getAnnotation(ExcelVOAttribute.class);
            cell = row.createCell(j);
            try {
                this.setCellValue(attr,field.get(vo),wb,cell);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    //根据注解类判断字段类型，并设置excel单元格数据格式方法
    private void setCellValue(ExcelVOAttribute attr, Object valueObject,SXSSFWorkbook workbook,Cell cell) {
        String returnValue;
        if (attr.isNumber()) {
            cell.setCellStyle(getCellStyle(attr,workbook));
            returnValue = valueObject == null || "".equals(valueObject) ? "0" : valueObject.toString();
            BigDecimal num = new BigDecimal(returnValue);
            cell.setCellValue(num.doubleValue());
        } else {
            returnValue = valueObject == null ? "" : valueObject.toString();
            cell.setCellValue(returnValue);
        }
    }

    //根据注解类判断字段类型，并返回excel单元格数据格式方法
    private CellStyle getCellStyle(ExcelVOAttribute attr, SXSSFWorkbook workbook) {
       if (attr.isNumber()) {
            if (decimalCellStyle == null) {
                decimalCellStyle = workbook.createCellStyle();
                DataFormat df = workbook.createDataFormat(); //此处设置数据格式  
                decimalCellStyle.setDataFormat(df.getFormat("#,#0.00"));
            }
            return decimalCellStyle;
        }
        return null;
    }

    // 创建工作页
    public Sheet createSheet(SXSSFWorkbook wb, String sheetName) {

        return wb.createSheet(sheetName);
    }

    //设置excel列头及格式
    public void setColumnTitle(Sheet sheet) {
        if (fields == null) {
           this.fields = this.getSortFields();
        }
        Row row;
        Cell cell;// 产生单元格
        ExcelVOAttribute attr;
        Field field;
        int fieldSize = fields.size();
        row = sheet.createRow(0);// 产生一行
        for (int i = 0; i < fieldSize; i++) {
            field = fields.get(i);
            attr = field.getAnnotation(ExcelVOAttribute.class);
            cell = CellUtil.createCell(row, i, attr.name());
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列中写入内容为String类型
            // 设置自动适应宽度，先统一都设置一次宽度，如后面特殊宽度会覆盖这里的设置
            sheet.setColumnWidth(i, attr.name().getBytes().length * 256);
        }
    }

    //获取输出对象字段列表，并根据注解进行字段排序方法
    private List<Field> getSortFields(){
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(x -> x.isAnnotationPresent(ExcelVOAttribute.class)).collect(Collectors.toList());
        List<Field> sortList = new ArrayList<>(fields);
        for(int i=0;i<fields.size();i++){//排序
            Field field = fields.get(i);
            ExcelVOAttribute excelVOAttribute = field.getAnnotation(ExcelVOAttribute.class);
            int sortNo = excelVOAttribute.column();
            sortList.set(sortNo,field);
        }
        return sortList;
    }

}



