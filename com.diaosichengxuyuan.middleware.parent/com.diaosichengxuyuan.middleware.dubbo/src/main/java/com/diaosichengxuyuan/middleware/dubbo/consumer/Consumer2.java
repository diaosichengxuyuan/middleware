package com.diaosichengxuyuan.middleware.dubbo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.diaosichengxuyuan.middleware.dubbo.model.MyModel;
import com.diaosichengxuyuan.middleware.dubbo.model.Service2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class Consumer2 {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] {"META-INF/spring/consumer.xml"});
        context.start();
        Service2 service2 = (Service2)context.getBean("service2");

        while(true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                MyModel myModel = service2.getModel("liuhaipeng");
                System.out.println(
                    "时间" + System.currentTimeMillis() + " 从" + RpcContext.getContext().getRemoteAddress() + "收到消息 "
                        + myModel);
            } catch(Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
