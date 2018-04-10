package com.wxmp.gather.ctrl;

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
@RequestMapping("/gather/auth")
public class AuthCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(AuthCtrl.class);


	@RequestMapping(value = "/regist.html")
	public ModelAndView regist(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("gather/auth/regist");

		return mv;
	}
	
}
