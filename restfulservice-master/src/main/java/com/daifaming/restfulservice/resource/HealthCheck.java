package com.daifaming.restfulservice.resource;

import javax.ws.rs.*;

import com.daifaming.restfulservice.domain.User;
import com.daifaming.restfulservice.scheduler.TestTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.daifaming.restfulservice.delegate.HealthCheckDelegate;

import java.awt.*;

@Path("/test")
public class HealthCheck {
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheck.class);
	@Autowired
	private HealthCheckDelegate healthCheckDelegate;

	@Path("/healthCheck")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public User healthCheck(User user) {
		System.out.println(user.getId());
		LOGGER.info("userName={}",user.getUserName());
		healthCheckDelegate.check();
		return  user;
	}
	@Path("/healthCheck")
	@GET
	public User healthCheck1(@QueryParam("userName") String name,@QueryParam("id")String id) {

		LOGGER.info("userName={},id={}",name,id);
		healthCheckDelegate.check();
		User user =new User();
		user.setId(id);
		user.setUserName(name);

		return  user;
	}
}
