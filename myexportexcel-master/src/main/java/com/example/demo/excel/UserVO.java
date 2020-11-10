package com.example.demo.excel;


import java.io.Serializable;
import java.math.BigDecimal;


public class UserVO implements Serializable {
    @ExcelVOAttribute(name = "用户ID", column = 0)
    private Integer userId;
    @ExcelVOAttribute(name = "用户姓名", column = 1)
    private String realName;
    @ExcelVOAttribute(name = "工资", column = 2,isNumber = true)
    private BigDecimal amount;

    public UserVO(Integer userId, String realName, BigDecimal amount) {
        this.userId = userId;
        this.realName = realName;
        this.amount = amount;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}