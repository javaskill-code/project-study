package com.bochtec.mbts.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 阿里云短信接口配置类
 * @author: yangxf
 * @date: 2019/4/11 15:01
 */
@Slf4j
public class AliyunConfig {

    /* 短信API产品名称（短信产品名固定，无需修改） */
    private static final String product = "Dysmsapi";

    /* 短信API产品域名，接口地址固定，无需修改 */
    private static final String domain = "dysmsapi.aliyuncs.com";

    /* 短信发送 */
    public static SendSmsResponse sendSms(String phone, String template, String code, String redisPrefix, int seconds) throws ClientException {

//        ISysDictDataService sysDictDataService = SpringUtil.getBean(ISysDictDataService.class);
//        List<SysDictData> sysDictData = sysDictDataService.selectDictDataByType("sms-type");
//        Map<String, String> map = sysDictData.stream().collect(Collectors.toMap(SysDictData::getDictLabel, SysDictData::getDictValue));
        Map<String, String> map = new HashMap<>();
        /* 超时时间，可自主调整 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化acsClient,暂不支持region化 */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", map.get("accessKeyId"), map.get("accessKeySecret"));
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /* 组装请求对象-具体描述见控制台-文档部分内容 */
        SendSmsRequest request = new SendSmsRequest();
        /* 必填:待发送手机号 */
        request.setPhoneNumbers(phone);
        /* 必填:短信签名-可在短信控制台中找到 */
        request.setSignName(map.get("sign")); //TODO: 这里是你短信签名
        /* 必填:短信模板code-可在短信控制台中找到 */
        request.setTemplateCode(template); //TODO: 这里是你的模板code
        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            log.info("短信发送成功！手机号：\t" + phone + "\t验证码\t" + code);
//            ProfileService bean = SpringUtil.getBean(ProfileService.class);
//            bean.saveToRedis(redisPrefix + DPUtils.encryptOnlyNumber(phone), code, seconds);
//            String key = redisPrefix + SecurityConsts.PREFIX_SHIRO_ISEABLE_SEND_CODE + DPUtils.encryptOnlyNumber(phone);
//            JedisUtils jedisUtils = SpringUtil.getBean(JedisUtils.class);
//            jedisUtils.saveString(key, "", 60);
            return sendSmsResponse;
        } else {
            log.info("需要发送的手机验证码\t" + phone + "短信发送失败！\t" + sendSmsResponse.getMessage() + "\t短信发送状态码\t" + sendSmsResponse.getCode() + "\t" + sendSmsResponse.getBizId());
            return sendSmsResponse;
        }
    }

    /**
     * @Function: 生成验证码
     * @author: yangxf
     * @Date: 2019/4/11 15:30
     */
    public static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
