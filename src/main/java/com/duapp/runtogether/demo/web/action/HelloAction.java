package com.duapp.runtogether.demo.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.duapp.runtogether.account.bo.SysUser;
import com.duapp.runtogether.account.service.UserService;

@Controller  
@RequestMapping("hello")
public class HelloAction {
	
	private static Logger log = Logger.getLogger(HelloAction.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "hellojson", method = RequestMethod.GET) 
	@ResponseBody
    public Map<String, Object> hellojson() {  
		String username = "zhanghao_py";
		SysUser user = userService.findUserByUsername(username);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("status", 200);
		jsonMap.put("data", user.getUsername());
		jsonMap.put("id", user.getId());
		
        return jsonMap;
    }
	
	@RequestMapping(value = "hello/{username}", method = RequestMethod.GET)  
    public ModelAndView hello(@PathVariable String username) {  
//		String username = "zhanghao_py";
		Assert.notNull(username, "username can't be null.");
		 
		ModelAndView mav = new ModelAndView("index/index");
		mav.addObject("username", username);
        return mav;  
    }
}
