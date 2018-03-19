package com.zhangmin.restfulservice.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.cj.core.util.StringUtils;
import com.zhangmin.restfulservice.CommonRsp;
import com.zhangmin.restfulservice.common.util.DateUtilService;
import com.zhangmin.restfulservice.common.util.StringUtilService;
import com.zhangmin.restfulservice.dao.UserLoginDao;
import com.zhangmin.restfulservice.dao.UserLoginLogDao;
import com.zhangmin.restfulservice.domain.UserInfo;
import com.zhangmin.restfulservice.domain.UserLoginLogInfo;
import com.zhangmin.restfulservice.req.UserLoginOutReq;
import com.zhangmin.restfulservice.req.UserLoginReq;
import com.zhangmin.restfulservice.rsp.UserLoginRsp;  

@Path("/user")
public class UserResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	
	private UserLoginDao userLoginDao;
	
	@Autowired
	
	private UserLoginLogDao userLoginLogDao;

	@Autowired
	
	private StringUtilService stringUtilService;
	
	@Autowired
	
	private DateUtilService dateUtilService;
	
	
	@SuppressWarnings("static-access")
	@Path("/login-in")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public UserLoginRsp userLogin(@Context HttpServletRequest request, UserLoginReq user) {
		
		UserLoginRsp userLoginRsp = new UserLoginRsp();
		Map<String, String> param = new HashMap<String, String>();
		LOGGER.info("IP={}",stringUtilService.getIpAddr(request));
		
		if(StringUtils.isNullOrEmpty(user.getUserName()) ||StringUtils.isNullOrEmpty(user.getPassWord()))
		{
			userLoginRsp.setRetCode("-1");
			userLoginRsp.setRetText("参数为空");
			
			return userLoginRsp;
		}
		if (stringUtilService.validateEmail(user.getUserName())) {
			param.put("email", user.getUserName());
		} else if (stringUtilService.validateMobile(user.getUserName())) {
			param.put("mobilePhone", user.getUserName());
		} else {
			param.put("customerName", user.getUserName());
		}

		param.put("passWord", user.getPassWord());
		UserInfo userInfo = new UserInfo();
		userInfo = userLoginDao.getUserInfo(param);
		if(userInfo ==null) {
			userLoginRsp.setRetCode("-1");
			userLoginRsp.setRetText("用户不存在");
			return userLoginRsp;

		}
		//登录成功。添加登录成功日志
		UserLoginLogInfo userLoginLogInfo=new UserLoginLogInfo();
		userLoginLogInfo.setCustomerId(userInfo.getCustomerId());
		userLoginLogInfo.setIPadress(stringUtilService.getIpAddr(request));
		userLoginLogInfo.setType(0);
		userLoginLogInfo.setCreateTime(dateUtilService.getDateTime());
		userLoginLogDao.insert(userLoginLogInfo);
		userLoginRsp.setRetCode("0");
		userLoginRsp.setRetText("成功");

		userLoginRsp.setUserinfo(userInfo);

		HttpSession session = request.getSession();
		session.setAttribute(userInfo.getCustomerId(), userInfo.getCustomerId());
		LOGGER.info("session={}", session.getAttribute(userInfo.getCustomerId()));
		return userLoginRsp;
		

	}
	@SuppressWarnings("static-access")
	@Path("/login-out")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public CommonRsp userLoginOut(@Context HttpServletRequest request, UserLoginOutReq user)
	
	{
		
		CommonRsp commonRsp=new CommonRsp();
		
		if(StringUtils.isNullOrEmpty(user.getCustomerId()))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("参数为空");
			
			return commonRsp;
		}
		//检查用户是否存在
		UserInfo userInfo = new UserInfo();
		userInfo = userLoginDao.checkUser(user.getCustomerId());
		if(userInfo ==null) {
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("用户不存在");
			return commonRsp;

		}
		HttpSession session = request.getSession();
		LOGGER.info("session={}", session.getAttribute(user.getCustomerId()));
		if(StringUtils.isNullOrEmpty((String) session.getAttribute(user.getCustomerId())))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("会话超时");
			return commonRsp;
		}
		session.removeAttribute(user.getCustomerId());
		//退出登录
		UserLoginLogInfo userLoginLogInfo=new UserLoginLogInfo();
		userLoginLogInfo.setCustomerId(user.getCustomerId());
		userLoginLogInfo.setIPadress(stringUtilService.getIpAddr(request));
		userLoginLogInfo.setType(1);
		userLoginLogInfo.setCreateTime(dateUtilService.getDateTime());
		userLoginLogDao.insert(userLoginLogInfo);
		commonRsp.setRetCode("0");
		commonRsp.setRetText("成功");
		return commonRsp;
		
		
	}
}
