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
@TableName("tb_trading_record")
@ApiModel(value = "TradingRecord对象", description = "")
public class TradingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "转出银行卡")
    @TableField("from_cardNo")
    private String fromCardno;

    @ApiModelProperty(value = "转出银行")
    private String fromBank;

    @ApiModelProperty(value = "转入银行卡")
    @TableField("to_cardNo")
    private String toCardno;

    @ApiModelProperty(value = "转入银行")
    private String toBank;

    @ApiModelProperty(value = "转账类型")
    private String type;

    @ApiModelProperty(value = "转入金额")
    private BigDecimal balance;

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
