package com.bochtec.mbts.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("转账模型")
public class TransferVo {


    /**
     * 收款人姓名
     */
    @ApiModelProperty("收款人姓名")
    private String BeneficiaryName;
    /**
     * 收款行
     */
    @ApiModelProperty("收款行")
    private String BeneficiaryBank;
    /**
     * 收款行
     */
    @ApiModelProperty("收款银行卡号")
    private String beneficiaryAccountNo;

    /**
     * 转账金额
     */
    @ApiModelProperty("转账金额")
    private String transferAmount;

    /**
     * 付款卡
     */
    @ApiModelProperty("付款卡")
    private String debitCard;
    /**
     * 到账类型   1  实时到账  2 2小时到账  3 次日到账
     */
    @ApiModelProperty("到账类型   1  实时到账  2 2小时到账  3 次日到账")
    private String accountingType;
    /**
     * 签名参数
     */
    @ApiModelProperty("签名参数")
    private String signature;


}
