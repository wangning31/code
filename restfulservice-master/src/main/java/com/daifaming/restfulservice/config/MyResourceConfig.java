package com.daifaming.restfulservice.config;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig
{
    public MyResourceConfig() {
        // 服务类所在的包路径
        packages("com.daifaming.restfulservice.resource");
        //注册JSON转换器
        register(JacksonJsonProvider.class);
    }
}
