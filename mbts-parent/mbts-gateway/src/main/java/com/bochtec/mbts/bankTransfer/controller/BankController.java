package com.bochtec.mbts.bankTransfer.controller;


import com.bochtec.mbts.bankTransfer.entity.Bank;
import com.bochtec.mbts.bankTransfer.service.IBankService;
import com.bochtec.mbts.vo.BaseResponse;
import com.bochtec.mbts.vo.LoginVo;
import com.bochtec.mbts.vo.TransferVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李振江
 * @since 2020-10-21
 */
@Api(tags = "银行卡账户信息管理接口")
@RestController
@RequestMapping("/bankTransfer/bank")
public class BankController {
    @Resource
    private IBankService bankService;

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return User 用户实体
     */
    @Operation(summary = "银行卡详情,针对得到单个银行卡的信息")
    @ApiOperation(value = "银行卡详情", notes = "获取银行卡信息", response = Bank.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", dataTypeClass = Long.class, paramType = "query", example = "12345"),
            @ApiImplicitParam(name = "goodsid", value = "商品id", dataTypeClass = Integer.class, paramType = "query", example = "12345"),
            @ApiImplicitParam(name = "mobile", value = "手机号", dataTypeClass = String.class, paramType = "query", example = "13866668888"),
            @ApiImplicitParam(name = "comment", value = "发货备注", dataTypeClass = String.class, paramType = "query", example = "请在情人节当天送到")
    })
    @GetMapping("/getUser")
    public Bank getUser(@RequestParam("id") Integer id) {
        return bankService.getById(id);
    }


    /**
     * 登录接口
     *
     * @return User 用户实体
     */
    @Operation(summary = "登录接口,针对得到单个银行卡的信息")
    @ApiOperation(value = "登录接口", notes = "获取银行卡信息", response = BaseResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNum", value = "手机号码", dataTypeClass = String.class, paramType = "query", example = "12345"),
            @ApiImplicitParam(name = "cardNo", value = "卡号", dataTypeClass = String.class, paramType = "query", example = "12345"),
            @ApiImplicitParam(name = "IdCard", value = "身份证号", dataTypeClass = String.class, paramType = "query", example = "13866668888"),
            @ApiImplicitParam(name = "passwd", value = "密码", dataTypeClass = String.class, paramType = "query", example = "13866668888"),
            @ApiImplicitParam(name = "graphValidateCode", value = "图形验证码", dataTypeClass = String.class, paramType = "query", example = "请在情人节当天送到")
    })
    @GetMapping("/login")
    public BaseResponse login(LoginVo loginVo) {
        BaseResponse response = bankService.loginService(loginVo);
        return response;
    }

    @Operation(summary = "转账接口,针对得到单个银行卡的信息")
    @ApiOperation(value = "转账接口", notes = "转账接口", response = BaseResponse.class)
    @PostMapping("/bankTransfer")
    public BaseResponse bankTransfer(@Valid TransferVo transferVo) {
        return bankService.bankTransfer(transferVo);
    }


    @Operation(summary = "转账接口,针对得到单个银行卡的信息")
    @ApiOperation(value = "执行转账接口", notes = "执行转账接口", response = BaseResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferId", value = "转账记录id", dataTypeClass = Integer.class, paramType = "query", example = "12345", required = true),
    })
    @PostMapping("/transfer")
    public BaseResponse doBankTransfer(Integer transferId) {
        return bankService.doBankTransfer(transferId);
    }


}
