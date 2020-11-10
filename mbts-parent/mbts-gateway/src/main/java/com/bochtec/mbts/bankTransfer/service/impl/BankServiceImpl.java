package com.bochtec.mbts.bankTransfer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bochtec.mbts.bankTransfer.entity.Bank;
import com.bochtec.mbts.bankTransfer.entity.TradingLog;
import com.bochtec.mbts.bankTransfer.entity.TradingRecord;
import com.bochtec.mbts.bankTransfer.mapper.BankMapper;
import com.bochtec.mbts.bankTransfer.mapper.TradingLogMapper;
import com.bochtec.mbts.bankTransfer.mapper.TradingRecordMapper;
import com.bochtec.mbts.bankTransfer.service.IBankService;
import com.bochtec.mbts.vo.BaseResponse;
import com.bochtec.mbts.vo.LoginVo;
import com.bochtec.mbts.vo.StatusCodeEnum;
import com.bochtec.mbts.vo.TransferVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李振江
 * @since 2020-10-21
 */
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements IBankService {

    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private TradingRecordMapper tradingRecordMapper;


    @Autowired
    private TradingLogMapper tradingLogMapper;

    @Override
    public BaseResponse loginService(LoginVo loginVo) {
        BaseResponse response;
        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        Bank bank;
        switch (loginVo.getType()) {
            case "1":
                //手机号登录
                boolean empty = StringUtils.isEmpty(loginVo.getPhoneNum());
                System.err.println(empty);
                if (!StringUtils.isEmpty(loginVo.getPhoneNum())) {
                    response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMessage());
                } //创建对象
                else {
                    queryWrapper.eq("phone", loginVo.getPhoneNum());
                    bank = bankMapper.selectOne(queryWrapper);
                    if (bank == null) {
                        response = new BaseResponse<>(StatusCodeEnum.NODATA.getCode(), StatusCodeEnum.NODATA.getMessage());

                    } else {
                        response = new BaseResponse<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), bank);
                    }
                }
                break; //可选
            case "2":
                // 卡号
                if (!StringUtils.isEmpty(loginVo.getCardNo())) {
                    response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMessage());
                } else {
                    queryWrapper.eq("cardNo", loginVo.getCardNo());
                    bank = bankMapper.selectOne(queryWrapper);
                    if (bank == null) {
                        response = new BaseResponse<>(StatusCodeEnum.NODATA.getCode(), StatusCodeEnum.NODATA.getMessage());
                    } else {
                        response = new BaseResponse<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), bank);
                    }
                }
                break; //可选
            case "3":
                // 身份证号
                if (!StringUtils.isEmpty(loginVo.getCardNo())) {
                    response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMessage());
                } else {
                    queryWrapper.eq("idNum", loginVo.getIdCard());
                    bank = bankMapper.selectOne(queryWrapper);
                    if (bank == null) {
                        response = new BaseResponse<>(StatusCodeEnum.NODATA.getCode(), StatusCodeEnum.NODATA.getMessage());
                    } else {
                        response = new BaseResponse<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), bank);
                    }
                }
                break; //可选
            //你可以有任意数量的case语句
            default: //可选
                response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMessage());
        }
        return response;
    }


    @Override
    public BaseResponse bankTransfer(TransferVo transferVo) {
        BaseResponse response;
        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cardNo", transferVo.getDebitCard());
        Bank bank = bankMapper.selectOne(queryWrapper);
        if (bank == null) {
            response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), "付款卡不存在");
        } else {
            // 校验余额事是否可用

            BigDecimal balance = bank.getBalance();
            BigDecimal bigDecimal = new BigDecimal(transferVo.getTransferAmount());

            if (balance.compareTo(bigDecimal) == -1) {
                response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), "付款卡余额不足");
            } else {
                TradingLog tradingLog = new TradingLog();
                tradingLog.setDescription("收到" + transferVo.getDebitCard() + "账户张转报文(" + transferVo.toString() + ")");
                tradingLog.setCreateTime(LocalDateTime.now());
                tradingLogMapper.insert(tradingLog);
                TradingRecord tradingRecord = new TradingRecord();
                tradingRecord.setFromCardno(bank.getCardNo());
                tradingRecord.setFromBank(bank.getIssuingBank());
                tradingRecord.setToCardno(transferVo.getBeneficiaryAccountNo());
                tradingRecord.setToBank(transferVo.getBeneficiaryBank());
                /**
                 *  收款人姓名
                 */
                tradingRecord.setCreateBy(transferVo.getBeneficiaryName());
                // 转账金额
                tradingRecord.setBalance(new BigDecimal(transferVo.getTransferAmount()));
                tradingRecord.setCreateTime(LocalDateTime.now());
                // 转账状态未完成
                tradingRecord.setEnableMark(0);
                //签名参数
                tradingRecord.setRemark(transferVo.getSignature());
                // 转账类型
                tradingRecord.setType(transferVo.getAccountingType());
                tradingRecordMapper.insert(tradingRecord);
                /**
                 *   todo  发送短信
                 */


                /**
                 *   todo  云签服务器签名
                 */


                /**
                 *  记录日志
                 */
                TradingLog tradingLog1 = new TradingLog();
                String desc = "启动" + transferVo.getDebitCard() + "账户身份验证,发送网络随机数、手机短信验证码；等待客户端提供认证数据";
                tradingLog1.setDescription(desc);
                tradingLog1.setCreateTime(LocalDateTime.now());
                tradingLogMapper.insert(tradingLog1);

                response = new BaseResponse<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), tradingRecord.getId());
            }

        }

        return response;
    }

    /**
     * 转账服务
     *
     * @param transferVo
     * @return
     */
    @Override
    public BaseResponse doBankTransfer(Integer transferVo) {

        BaseResponse response;

        TradingRecord tradingRecord = tradingRecordMapper.selectById(transferVo);


        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cardNo", tradingRecord.getFromCardno());

        Bank bank = bankMapper.selectOne(queryWrapper);

        if (bank.getBalance().compareTo(tradingRecord.getBalance()) == -1) {
            response = new BaseResponse<>(StatusCodeEnum.ERROR.getCode(), "付款卡余额不足");
        } else {
            BigDecimal subtract = bank.getBalance().subtract(tradingRecord.getBalance());
            bank.setBalance(subtract);
            tradingRecord.setEnableMark(1);
            // 设置转账状态
            bankMapper.updateById(bank);
            tradingRecordMapper.updateById(tradingRecord);
            // 操作记录日志，返回给前端
            TradingLog tradingLog = new TradingLog();
            String desc = "接受完整签名并验证后，完成" + tradingRecord.getFromCardno() + "账户转账操作，记入后台业务系统，并发送电子回单至客户端";
            tradingLog.setDescription(desc);
            tradingLog.setCreateTime(LocalDateTime.now());
            tradingLogMapper.insert(tradingLog);
            response = new BaseResponse<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage());
        }
        return response;
    }
}
