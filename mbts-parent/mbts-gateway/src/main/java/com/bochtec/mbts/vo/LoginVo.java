package com.bochtec.mbts.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("登录模型")
public class LoginVo {

    @ApiModelProperty("登录类型 1 为手机 2 卡号  3 身份证号")

    private String type;
    @ApiModelProperty("手机号码")
//    @NotBlank(message = "请输入有效的手机号码")
//    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "请输入有效的手机号码")
    private String phoneNum;
    @ApiModelProperty("卡号")
//    @NotBlank(message = "请输入有效的手机号码")
//    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "请输入有效的手机号码")
    private String cardNo;
    @ApiModelProperty("身份证号")
//    @NotBlank(message = "请输入有效的手机号码")
//    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "请输入有效的手机号码")
    private String IdCard;
    @ApiModelProperty("密码")
    private String passwd;
    @ApiModelProperty("图形验证码")
    private String graphValidateCode;
}
