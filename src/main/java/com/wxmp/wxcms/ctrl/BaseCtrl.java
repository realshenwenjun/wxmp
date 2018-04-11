package com.wxmp.wxcms.ctrl;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller基础公共类
 *
 * @author mengLei
 * @date 2017 -06-28 20:41:23
 */
public class BaseCtrl {
    
    @Resource
    protected HttpServletRequest request;
    
    @Resource
    protected HttpServletResponse response;
    
    /**
     * 初始化属性，在每个方法执行前执行该赋值方法
     *
     * @param request
     * @param response
     * @author mengLei
     * @date 2017 -10-17 15:57:10
     */
    @ModelAttribute
    public void setAttribute(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public JSON getJsonResponse(boolean success, int code, String msg, Object obj){
        JSONObject j = new JSONObject();
        j.put("success",success);
        j.put("code",code);
        j.put("msg",msg);
        j.put("obj",obj);
        return j;
    }
}
