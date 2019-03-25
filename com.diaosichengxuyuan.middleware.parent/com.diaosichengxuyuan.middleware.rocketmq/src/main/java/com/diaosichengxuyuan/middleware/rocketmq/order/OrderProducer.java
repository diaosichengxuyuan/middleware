package com.diaosichengxuyuan.middleware.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class OrderProducer {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_producer_group");
            producer.setNamesrvAddr("localhost:9876");
            producer.start();

            List<OrderDemo> orders = buildOrders();

            String[] tags = new String[] {"TagA", "TagB", "TagC"};
            for(int i = 0; i < 10; i++) {
                Message msg = new Message("TopicTestOrder", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + orders.get(i)).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        long id = (long)arg;
                        //同一个订单id发送到同一个队列中
                        int index = (int)(id % mqs.size());
                        return mqs.get(index);
                    }
                }, orders.get(i).getOrderId());

                System.out.printf("发送结果%s   发送的消息%s \n", sendResult, msg);
            }

            producer.shutdown();
        } catch(MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成模拟订单数据
     */
    private static List<OrderDemo> buildOrders() {
        List<OrderDemo> orderList = new ArrayList<>();

        OrderDemo orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderDemo();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }

    public static class OrderDemo {
        private Long orderId;

        private String desc;

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Long getOrderId() {
            return orderId;
        }

        public String getDesc() {
            return desc;
        }

        @Override
        public String toString() {
            return orderId + " " + desc;
        }
    }
}
