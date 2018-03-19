package com.zhangmin.restfulservice.config;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig
{
    public MyResourceConfig() {
     
        packages("com.zhangmin.restfulservice.resource");

        register(JacksonJsonProvider.class);
    }
}
