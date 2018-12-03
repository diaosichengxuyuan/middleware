/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
