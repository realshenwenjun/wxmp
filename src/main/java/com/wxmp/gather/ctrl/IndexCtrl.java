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
@RequestMapping("/gather/index")
public class IndexCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(IndexCtrl.class);

	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("gather/index");

		return mv;
	}
}
