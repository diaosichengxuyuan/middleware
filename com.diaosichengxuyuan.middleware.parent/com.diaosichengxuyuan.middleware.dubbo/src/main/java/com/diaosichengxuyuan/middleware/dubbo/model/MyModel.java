package com.diaosichengxuyuan.middleware.dubbo.model;

import java.io.Serializable;

/**
 * @author liuhaipeng
 * @date 2018/10/7
 */
public class MyModel implements Serializable {

    private String name;

    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "name:" + name + " age:" + age;
    }
}
