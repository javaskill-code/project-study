package com.bochtec.mbts.bankTransfer.service;

import com.bochtec.mbts.bankTransfer.entity.Bank;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bochtec.mbts.vo.BaseResponse;
import com.bochtec.mbts.vo.LoginVo;
import com.bochtec.mbts.vo.TransferVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李振江
 * @since 2020-10-21
 */
public interface IBankService extends IService<Bank> {

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    BaseResponse loginService(LoginVo loginVo);

    /**
     * 预转账业务
     *
     * @param transferVo
     * @return
     */

    BaseResponse bankTransfer(TransferVo transferVo);

    /**
     * 转账服务
     *
     * @param transferVo
     * @return
     */
    BaseResponse doBankTransfer(Integer transferVo);
}
