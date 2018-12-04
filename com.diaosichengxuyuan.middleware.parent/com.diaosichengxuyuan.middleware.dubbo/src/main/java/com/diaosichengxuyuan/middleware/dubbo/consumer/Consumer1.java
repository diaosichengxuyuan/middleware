package com.diaosichengxuyuan.middleware.dubbo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.diaosichengxuyuan.middleware.dubbo.model.Service1;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class Consumer1 {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] {"META-INF/spring/consumer.xml"});
        context.start();
        Service1 service1 = (Service1)context.getBean("service1");

        while(true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                String hello = service1.sayHello("hello,i am sevice1");
                System.out.println(
                    "时间" + System.currentTimeMillis() + " 从" + RpcContext.getContext().getRemoteAddress() + "收到消息 "
                        + hello);
            } catch(Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
