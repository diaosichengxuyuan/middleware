package com.diaosichengxuyuan.middleware.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] {"META-INF/spring/provider.xml"});
        context.start();
        System.in.read();
    }

}
