package com.example.springdemo.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { java.lang.annotation.ElementType.FIELD })
public @interface ExcelVOAttribute {
    /**
     * 导出到Excel中的名字.
     */
    public abstract String name();
    /**
     * 配置列的展现顺序,对应A(0),B(1),C(2),D(3)....
     */
    public abstract int column();

    /**
     * 是否需要数字展示
     */
    public abstract boolean isNumber() default false;

}
