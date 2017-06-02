package com.tangzongyun.basesystem.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangzongyun.basesystem.com.BaseController;
import com.tangzongyun.basesystem.sys.domain.User;
import com.tangzongyun.basesystem.sys.repository.UserRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
@Controller
@RequestMapping(value="login")
public class LoginController extends BaseController{
	@Autowired
	UserRepository repository;
	@RequestMapping(value = "login",method = RequestMethod.POST,produces ="application/json; charset=utf-8" )
	@ResponseBody
	@ApiOperation(value = "登录验证")
	public String login2(
	@ApiParam(required = true, name = "loginName", value = "登录账号") 
	@RequestParam(value = "loginName") String loginName,
    @ApiParam(required = true, name = "password", value = "登录密码") 
	@RequestParam(value = "password") String password,
	HttpServletRequest request){
		 // 获取subject对象
       Subject subject = SecurityUtils.getSubject();
       // 实例化用户名密码令牌
       UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
       try {
           // 使用subject对象进行登陆
           subject.login(token);
           // 获取session
           Session session = subject.getSession();
           // 输出session
           System.out.println("sessionId:" + session.getId() + ";sessionHost:" + session.getHost() +";sessionTimeout:%s" + session.getTimeout());
           session.setAttribute("info", "session的数据");
           return "redirect:success";
       } catch (Exception e) {
           // 验证失败
           e.printStackTrace();
           request.setAttribute("errorMsg", "用户名或密码错误");
           return "login";
       }
	}
	
}
