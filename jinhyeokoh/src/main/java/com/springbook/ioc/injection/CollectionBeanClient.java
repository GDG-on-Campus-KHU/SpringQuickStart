package com.springbook.ioc.injection;

import java.util.Map;
import java.util.Properties;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
    public static void main(String[] args) {
        AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

        CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
        Properties addressList = bean.getAddressList();
        for (Map.Entry<Object, Object> entry : addressList.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.out.println(key + " : " + value);
        }
        factory.close();
    }
}