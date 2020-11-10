package com.bochtec.mbts.bankTransfer.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 李振江
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_bank")
@ApiModel(value = "Bank对象", description = "")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "持卡人姓名")
    private String name;

    @ApiModelProperty(value = "银行卡预留手机号")
    private String phone;

    @ApiModelProperty(value = "银行卡号")
    @TableField("cardNo")
    private String cardNo;

    @ApiModelProperty(value = "身份证号")
    @TableField("idNum")
    private String idNum;

    @ApiModelProperty(value = "银行卡密码")
    private String passwd;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "发卡行")
    private String issuingBank;

    @ApiModelProperty(value = "图形验证码")
    private String graphValidateCode;

    @ApiModelProperty(value = "短信验证码")
    private String validateCode;

    @ApiModelProperty(value = "盐加密")
    private String salt;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "1 删除 0 未删除")
    private Integer deleteMark;

    @ApiModelProperty(value = "1无效 0有效")
    private Integer enableMark;

    @ApiModelProperty(value = "备注")
    private String remark;


}
