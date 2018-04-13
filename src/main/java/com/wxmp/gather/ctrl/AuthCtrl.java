package com.wxmp.gather.ctrl;

import com.wxmp.core.util.*;
import com.wxmp.gather.constant.GatherMessage;
import com.wxmp.gather.domain.User;
import com.wxmp.gather.service.AuthService;
import com.wxmp.wxapi.process.WxMemoryCacheClient;
import com.wxmp.wxcms.ctrl.BaseCtrl;
import net.sf.json.JSON;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/gather/auth")
public class AuthCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(AuthCtrl.class);
	@Autowired
	AuthService authService;

	static String VERIFICATION_CODE_START = "verification_code_start";
	static String VERIFICATION_CODE = "verification_code";

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
	@RequestMapping(value = "/login.html")
	public ModelAndView loginHtml(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("gather/auth/login");

		return mv;
	}


	@RequestMapping(value = "/regist")
	@ResponseBody
	public JSON regist(HttpServletRequest request, User user, String verificationCode) throws Exception{
		String code = (String) CacheUtils.get(VERIFICATION_CODE + WebUtil.getRemoteAddr(request));
		if (!verificationCode.equals(code))
			return getJsonResponse(false, GatherMessage.VERIFICATION_CODE_WRONG,GatherMessage.VERIFICATION_CODE_WRONG_NAME,null);
		User exist = authService.getUserByPhone(user.getUserPhone());
		if (exist != null)
			return getJsonResponse(false, GatherMessage.USER_PHONE_EXIST,GatherMessage.USER_PHONE_EXIST_NAME,null);
		user.setId(StringUtil.getRandomLengthString(32));
		user.setPassword(MD5Util.getMD5Code(user.getPassword()));
		user.setCreateTime(new Date());
		authService.addUser(user);
		return getJsonResponse(true,GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,null);
	}
	@RequestMapping(value = "/login")
	@ResponseBody
	public JSON login(HttpServletRequest request, User user) throws Exception{
		User u = authService.getUserByPhone(user.getUserPhone());
		if (u ==null || !u.getPassword().equals(MD5Util.getMD5Code(user.getPassword())))
			return getJsonResponse(false, GatherMessage.PASSWORD_WRONG,GatherMessage.PASSWORD_WRONG_NAME,null);
		User update = new User();
		update.setId(u.getId());
		String sessionid = request.getSession().getId();
		String openid = WxMemoryCacheClient.getOpenid(sessionid);
		if (!StringUtil.isEmpty(openid)){
			update.setWxOpenid(openid);
			authService.updateUser(update);// 更新openid
		}
		Object redirectUrl = SessionUtil.session.getAttribute(SessionUtil.REDIRECT_URL);
		SessionUtil.session.removeAttribute(SessionUtil.REDIRECT_URL);
		SessionUtil.setGatherUserId(u.getId());
		log.info("|-----------------------redirectUrl-----------------| " + redirectUrl);
		return getJsonResponse(true,GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,redirectUrl);
	}

	@RequestMapping(value = "/send/sms")
	@ResponseBody
	public JSON sendSms(HttpServletRequest request, String userPhone) throws Exception{
		CacheUtils.put(VERIFICATION_CODE_START + WebUtil.getRemoteAddr(request), System.currentTimeMillis());
		String code = "1234";
		CacheUtils.put(VERIFICATION_CODE + WebUtil.getRemoteAddr(request), code);
		return getJsonResponse(true,GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,null);
	}
}
