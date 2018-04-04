package com.wxmp.rent.ctrl;

import com.wxmp.wxcms.ctrl.BaseCtrl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/rent/manager/user")
public class ManagerUserCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(ManagerUserCtrl.class);
	

	
	@RequestMapping(value = "/demo.html")
	public ModelAndView sendmsg(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("rent/manager/user/weui");

		return mv;
	}
	@RequestMapping(value = "/bindWx.html")
	public ModelAndView bind(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("rent/manager/user/bindWx");

		return mv;
	}
	
}
