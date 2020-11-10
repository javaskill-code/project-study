package com.bochtec.mbts;

import com.bochtec.mbts.vo.BaseResponse;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class TestBeanDefintion {
    public static void main(String[] args) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClassName("BaseResponse");
        rootBeanDefinition.setBeanClass(BaseResponse.class);
        rootBeanDefinition.setScope("prototype");
        System.err.println(rootBeanDefinition);
    }
}
