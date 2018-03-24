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
import com.zhangmin.restfulservice.dao.PersonDao;
import com.zhangmin.restfulservice.dao.UserLoginDao;
import com.zhangmin.restfulservice.dao.UserLoginLogDao;
import com.zhangmin.restfulservice.domain.PersonInfo;
import com.zhangmin.restfulservice.domain.UserInfo;
import com.zhangmin.restfulservice.domain.UserLoginLogInfo;
import com.zhangmin.restfulservice.req.UserLoginOutReq;
import com.zhangmin.restfulservice.req.UserLoginReq;
import com.zhangmin.restfulservice.req.UserRegisterReq;
import com.zhangmin.restfulservice.rsp.UserLoginRsp;  

@Path("/user")
public class UserResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	
	private UserLoginDao userLoginDao;
	
	@Autowired
	
	private UserLoginLogDao userLoginLogDao;

	@Autowired
	
	private PersonDao personDao;
	
	@SuppressWarnings("static-access")
	@Path("/login-in")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public UserLoginRsp userLogin(@Context HttpServletRequest request, UserLoginReq user) {
		
		UserLoginRsp userLoginRsp = new UserLoginRsp();
		Map<String, String> param = new HashMap<String, String>();
		LOGGER.info("IP={}",StringUtilService.getIpAddr(request));
		
		if(StringUtils.isNullOrEmpty(user.getUserName()) ||StringUtils.isNullOrEmpty(user.getPassWord()))
		{
			userLoginRsp.setRetCode("-1");
			userLoginRsp.setRetText("参数为空");
			
			return userLoginRsp;
		}
		if (StringUtilService.validateEmail(user.getUserName())) {
			param.put("email", user.getUserName());
		} else if (StringUtilService.validateMobile(user.getUserName())) {
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
		userLoginLogInfo.setIPadress(StringUtilService.getIpAddr(request));
		userLoginLogInfo.setType(0);
		userLoginLogInfo.setCreateTime(DateUtilService.getDateTime());
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
		userLoginLogInfo.setIPadress(StringUtilService.getIpAddr(request));
		userLoginLogInfo.setType(1);
		userLoginLogInfo.setCreateTime(DateUtilService.getDateTime());
		userLoginLogDao.insert(userLoginLogInfo);
		commonRsp.setRetCode("0");
		commonRsp.setRetText("成功");
		return commonRsp;
		
		
	}
	
	@SuppressWarnings("static-access")
	@Path("/register")
	@POST
	@Produces( "application/json")
	@Consumes("application/json")
	public CommonRsp register(@Context HttpServletRequest request, UserRegisterReq req)
	
	{
		
		CommonRsp commonRsp=new CommonRsp();
		
		if (StringUtils.isNullOrEmpty(req.getCustomerName()) || StringUtils.isNullOrEmpty(req.getEmail())
				|| StringUtils.isNullOrEmpty(req.getPassWord()) || StringUtils.isNullOrEmpty(req.getPassWordAgain())) {
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("参数为空");

			return commonRsp;
		}

		if(!StringUtilService.validateNickname(req.getCustomerName()))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("账号参数不合法");

			return commonRsp;
		}
		if(!StringUtilService.validateEmail(req.getEmail()))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("邮箱参数不合法");

			return commonRsp;
		}
		if(!StringUtilService.validatePassword(req.getPassWord()))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("密码参数不合法");

			return commonRsp;
		}
		if(!req.getPassWord().equalsIgnoreCase(req.getPassWordAgain()))
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("两次密码不一致");

			return commonRsp;
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo = userLoginDao.checkUseByCustomerName(req.getCustomerName());
		if(userInfo !=null)
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("用户名已经存在");
			return commonRsp;
		}
		userInfo = userLoginDao.checkUserByEmail(req.getEmail());
		if(userInfo !=null)
		{
			commonRsp.setRetCode("-1");
			commonRsp.setRetText("邮箱已经注册");
			return commonRsp;
		}
		//录入登录信息
		UserInfo register = new UserInfo();
		register.setCreateTime(DateUtilService.getDateTime());
		register.setCustomerName(req.getCustomerName());
		register.setCustomerId(StringUtilService.getUUID());
		register.setEmail(req.getEmail());
		register.setMobilePhone(null);
		register.setPassWord(req.getPassWord());
		register.setStatus(0);
		register.setType(0);
		register.setUpdateTime(register.getCreateTime());
		userLoginDao.insert(register);
		//录入个人基本信息
		PersonInfo personInfo =new PersonInfo();
		personInfo.setCustomerName(req.getCustomerName());
		personInfo.setCreateTime(register.getCreateTime());
		personInfo.setCustomerId(register.getCustomerId());
		personInfo.setCustomerName(register.getCustomerName());
		personInfo.setEmail(register.getEmail());
		personInfo.setType(0);
		personInfo.setSex(0);
		personInfo.setUpdateTime(register.getCreateTime());
		personInfo.setEmail(req.getEmail());
	
		personDao.insert(personInfo);
		commonRsp.setRetCode("0");
		commonRsp.setRetText("用户注册成功");
		return commonRsp;
		
		
	}
}
