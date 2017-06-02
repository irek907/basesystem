package com.tangzongyun.basesystem.sys.web;

import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.tangzongyun.basesystem.com.BaseController;
import com.tangzongyun.basesystem.sys.domain.User;
import com.tangzongyun.basesystem.sys.repository.UserRepository;
import com.tangzongyun.basesystem.utils.ResultBean;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping({ "/user" })
public class UserController extends BaseController{
	@Autowired
	UserRepository repository;
	
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	@ApiOperation(value = "查询所有用户")
	public JSONObject index() {
		JSONObject json = new JSONObject();
		List<User> users = repository.findAll();
		json.put("data", users);
		return json;
	}
	@RequestMapping(value = "login",method = RequestMethod.POST,produces ="application/json; charset=utf-8" )
	@ResponseBody
	@ApiOperation(value = "登录验证")
    public JSONObject login(
    		
    		@ApiParam(required = true, name = "userName", value = "登录账号") 
    		@RequestParam(value = "userName") String userName,
    	    @ApiParam(required = true, name = "password", value = "登录密码") 
    		@RequestParam(value = "password") String password,
    		HttpServletRequest request) {
		JSONObject json = new JSONObject();
		User user = repository.findByLoginName(userName);
		ResultBean rBean = new ResultBean();
		if(user!=null){
			if(password.equals(user.getPassword())){ 
				rBean.setMsg("ok");
				rBean.setStatus("0");
			}else{
				rBean.setMsg("error");
				rBean.setStatus("1");
			}
		}else{
			rBean.setMsg("null");
			rBean.setStatus("1");
		}
		
		
		json.put("data", rBean);
		return json;
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	@ApiOperation(value = "查询所有用户")
	public String test(){
		List<User> usersList = repository.findAll();
		return buildSuccessResultInfo(usersList);
	}
	
/*	@RequestMapping(value = "testpost", method = RequestMethod.POST)
    @ResponseBody
    public String testpost(HttpServletRequest request,@RequestBody Map<String, Object> map) throws Exception {
		
	
		System.out.println("------------------------");
		
		
		for(Map.Entry<String, Object> entry:map.entrySet()){
			
			System.out.println(entry.getKey() + "        " + entry.getValue() + "     --      " + entry.getValue().getClass());
			
			
		}
		
		Enumeration em = request.getParameterNames();
		 while (em.hasMoreElements()) {
		    String name = (String) em.nextElement();
		    String value = request.getParameter(name);
		    
		    System.out.println("key=" + name + "           value=" +value  + "     --      " + value.getClass());
		}
		
        return "sss";
    }
	
	@RequestMapping(value = "testpost", method = RequestMethod.GET)
    @ResponseBody
    public String testget(String username, String password) throws Exception {
		System.out.println("--get--");
        return username + ":" + password;
    }*/
	/*
	@RequestMapping(method= RequestMethod.POST)  
	 public String processSubmit(@ModelAttribute( " pojo " ) Pojo pojo) {  
	 return " helloWorld " ;  
	 } 
	*/
}
