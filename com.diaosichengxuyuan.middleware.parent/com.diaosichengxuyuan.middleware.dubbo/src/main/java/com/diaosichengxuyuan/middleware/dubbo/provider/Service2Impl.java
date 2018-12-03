package com.diaosichengxuyuan.middleware.dubbo.provider;

import com.alibaba.dubbo.rpc.RpcContext;
import com.diaosichengxuyuan.middleware.dubbo.model.MyModel;
import com.diaosichengxuyuan.middleware.dubbo.model.Service2;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class Service2Impl implements Service2 {

    @Override
    public MyModel getModel(String name) {
        System.out.println(
            "时间" + System.currentTimeMillis() + " 从" + RpcContext.getContext().getRemoteAddress() + "收到消息 " + name);

        if(!"liuhaipeng".equals(name)) {
            return null;
        }

        MyModel myModel = new MyModel();
        myModel.setName("liuhaipeng");
        myModel.setAge(27);
        return myModel;
    }

}
