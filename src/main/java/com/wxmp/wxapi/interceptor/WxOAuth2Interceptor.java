package com.wxmp.wxapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxmp.core.util.SessionUtil;
import com.wxmp.gather.domain.User;
import com.wxmp.gather.service.AuthService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wxmp.core.util.HttpUtil;
import com.wxmp.wxapi.process.MpAccount;
import com.wxmp.wxapi.process.OAuthScope;
import com.wxmp.wxapi.process.WxApi;
import com.wxmp.wxapi.process.WxApiClient;
import com.wxmp.wxapi.process.WxMemoryCacheClient;

/**
 * 微信客户端用户请求验证拦截器
 */
public class WxOAuth2Interceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = LogManager.getLogger(WxOAuth2Interceptor.class);
	
	/**
	 * 开发者自行处理拦截逻辑，
	 * 方便起见，此处只处理includes
	 */
	private String[] excludes;//不需要拦截的
	private String[] includes;//需要拦截的

	private AuthService authService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		log.info("-------------------------------------preHandle-----<0>-------------------uri:"+uri);
		boolean oauthFlag = true;//为方便展示的参数，开发者自行处理是否auth认证的

		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") <= -1) {// 不是微信浏览器就不去获取openid了
			oauthFlag = false;
		}
		if (request.getServerName().contains("localhost") || request.getServerName().contains("127.0.0.1"))
			oauthFlag = false;
		if(oauthFlag){//如果需要oauth认证
			String sessionid = request.getSession().getId();
			String openid = WxMemoryCacheClient.getOpenid(sessionid);//先从缓存中获取openid
			log.info("-------------------------------------preHandle-----<1>-------------------openid:"+openid);

			if(StringUtils.isBlank(openid)){//没有，通过微信页面授权获取
				String code = request.getParameter("code");
				log.info("-------------------------------------preHandle-----<2-1>-------------------code:"+code);
				if(!StringUtils.isBlank(code)){//如果request中包括code，则是微信回调
					log.info("-------------------------------------preHandle-----<2-2>-------------------code:"+code);
					try {
						openid = WxApiClient.getOAuthOpenId(WxMemoryCacheClient.getSingleMpAccount(), code);
						log.info("-------------------------------------preHandle-----<2-3>-------------------openid:"+openid);
						if(!StringUtils.isBlank(openid)){
							WxMemoryCacheClient.setOpenid(sessionid, openid);//缓存openid
//							return true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{//oauth获取code
					MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
					log.info("-------------------------------------preHandle-----<3-1>-------------------mpAccount:"+mpAccount.getAccount());
					String redirectUrl = HttpUtil.getRequestFullUriNoContextPath(request);//请求code的回调url
					if(!HttpUtil.existHttpPath(redirectUrl)){
						//以上不存在就拼接全部url（包括context）
						redirectUrl=HttpUtil.getRequestFullUri(request);
					}
					log.info("-------------------------------------preHandle-----<3-2>-------------------redirectUrl:"+redirectUrl);
					String state = OAuth2RequestParamHelper.prepareState(request);
					log.info("-------------------------------------preHandle-----<3-3>-------------------state:"+state);
					String url = WxApi.getOAuthCodeUrl(mpAccount.getAppid(), redirectUrl, OAuthScope.Base.toString(), state);
					log.info("-------------------------------------preHandle-----<3-4>-------------------url:"+url);
					HttpUtil.redirectHttpUrl(request, response, url);
					return false;
				}
			}
			log.info("#### WxOAuth2Interceptor Session : openid = " + openid);
			String userId = SessionUtil.getGatherUserId();

			if(userId  == null){ // openid自动登录
				User u = authService.getUserByWxOpenId(openid);
				if (u != null){
					SessionUtil.setGatherUserId(u.getId());
				}
			}
		}
		boolean loginFlag = true;//为方便展示的参数，开发者自行处理是否需要登录
		for(String s : excludes){
			if(uri.contains(s)){//如果包含，就不拦截
				loginFlag = false;
				break;
			}
		}
		if (loginFlag){//如果需要登录

			String userId = SessionUtil.getGatherUserId();
			if(userId  == null){
				if (uri.contains(".html")) {
					String url = request.getRequestURI();
					String queryurl = request.getQueryString();
					if(null != queryurl){
						url += "?" + queryurl;
					}
					String baseUri = request.getContextPath();
					SessionUtil.session.setAttribute(SessionUtil.REDIRECT_URL,url);
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					response.sendRedirect(baseUri + "/gather/auth/login.html");
					return false;
				}else {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("success",false);
					jsonObject.put("code",response.SC_GATEWAY_TIMEOUT);
					jsonObject.put("msg","用户未登录");
					jsonObject.put("obj",null);

					response.getWriter().write(jsonObject.toString());
					return false;
				}
			}

		}else return true;

		return true;
	}
	
	
	public String[] getExcludes() {
		return excludes;
	}

	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}


	public void setIncludes(String[] includes) {
		this.includes = includes;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
}

