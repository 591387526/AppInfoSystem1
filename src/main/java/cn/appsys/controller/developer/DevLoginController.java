package cn.appsys.controller.developer;

import java.util.Date;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.appsys.entity.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.util.Constants;


@Controller
@RequestMapping(value="/dev")
public class DevLoginController {
	private Logger logger = Logger.getLogger(DevLoginController.class);
	
	@Resource
	private DevUserService devUserService;
	
	
	@RequestMapping(value="/updDevUser",method=RequestMethod.GET)
	public String getUpdUser(@PathVariable Integer id,Model model) {
		DevUser devuser=devUserService.getDevUserById(id);
		model.addAttribute("devuser",devuser);
		return "developer/updDevUser";
	}
	@RequestMapping(value="/updUserSave",method=RequestMethod.POST)
	public String getUpdUserSave(DevUser devuser,HttpSession session) {
		devuser.setModifyBy(((DevUser)session.getAttribute("devuser")).getId());
		devuser.setModifyDate(new Date());
		devUserService.updateDevUser(devuser);
		return "redirect:/flatform/main";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		logger.debug("LoginController welcome AppInfoSystem develpor==================");
		return "devlogin";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String devCode,@RequestParam String devPassword,HttpServletRequest request,HttpSession session){
		logger.debug("doLogin====================================");
		//调用service方法，进行用户匹配
		DevUser user = null;
		try {
			user = devUserService.login(devCode,devPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null != user){//登录成功
			//放入session
			session.setAttribute(Constants.DEV_USER_SESSION, user);
			//页面跳转（main.jsp）
			return "redirect:/dev/flatform/main";
		}else{
			//页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "用户名或密码不正确");
			return "devlogin";
		}
	}
	
	@RequestMapping(value="/flatform/main")
	public String main(HttpSession session){
		if(session.getAttribute(Constants.DEV_USER_SESSION) == null){
			return "redirect:/dev/login";
		}
		return "developer/main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		//清除session
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "devlogin";
	}
	@RequestMapping(value="/register")
	public String register(){
		logger.debug("registerController welcome AppInfoSystem develpor==================");
		return "devregister";
	}
	
	@RequestMapping(value="/doregister",method=RequestMethod.POST)
	public String doregister(@ModelAttribute("user")DevUser user, HttpServletRequest request){
		logger.debug("doregister====================================");
		//调用service方法，进行用户匹配
		DevUser u=devUserService.checkDevCode(user.getDevCode());
		if(u==null) {
			devUserService.register(user); 
			return "redirect:/dev/login";
		}else {
			request.setAttribute("error", "用户名已被注册");
			return "devregister";
		}

		
	}
}

