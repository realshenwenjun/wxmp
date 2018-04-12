package com.wxmp.gather.ctrl;

import com.wxmp.core.util.CacheUtils;
import com.wxmp.core.util.JSONUtil;
import com.wxmp.core.util.SessionUtil;
import com.wxmp.core.util.WebUtil;
import com.wxmp.gather.domain.User;
import com.wxmp.wxcms.ctrl.BaseCtrl;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.groovy.GJson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/gather/auth")
public class AuthCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(AuthCtrl.class);

	static String VERIFICATION_CODE_START = "verification_code_start";

	@RequestMapping(value = "/regist.html")
	public ModelAndView registHtml(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("gather/auth/regist");
		Object verificationCodeStart = CacheUtils.get(VERIFICATION_CODE_START + WebUtil.getRemoteAddr(request));
		if (verificationCodeStart != null) {
			int i = (int) ((System.currentTimeMillis() - (Long)verificationCodeStart)*1/1000);
			if (i < 60){
				mv.addObject("verificationCodeStart", i);
			}

		}
		return mv;
	}

	@RequestMapping(value = "/regist")
	@ResponseBody
	public JSON regist(HttpServletRequest request, User user){
		log.info(">>>>>>>>>>>>>>>>> : ");
		return getJsonResponse(true,0,"成功",null);
	}

	@RequestMapping(value = "/send/sms")
	@ResponseBody
	public JSON sendSms(HttpServletRequest request, String userPhone){
		log.info(">>>>>>>>>>>>>>>>> : " + WebUtil.getRemoteAddr(request));
		CacheUtils.put(VERIFICATION_CODE_START + WebUtil.getRemoteAddr(request), System.currentTimeMillis());
		return getJsonResponse(true,0,"成功",null);
	}
	
}
