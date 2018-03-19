package com.zhangmin.restfulservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangmin.restfulservice.delegate.HealthCheckDelegate;
import com.zhangmin.restfulservice.domain.UserInfo;

@Path("/test")
public class HealthCheck {
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheck.class);
	@Autowired
	private HealthCheckDelegate healthCheckDelegate;

	@Path("/healthCheck")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public UserInfo healthCheck(UserInfo user) {
		System.out.println(user.getId());
//		LOGGER.info("userName={}",user.getUserName());
		healthCheckDelegate.check();
		return  user;
	}
	@Path("/healthCheck")
	@GET
	public UserInfo healthCheck1(@QueryParam("userName") String name,@QueryParam("id")String id) {

		LOGGER.info("userName={},id={}",name,id);
		healthCheckDelegate.check();
		UserInfo user =new UserInfo();
		user.setId(id);
//		user.setUserName(name);

		return  user;
	}
}
