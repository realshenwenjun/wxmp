package com.wxmp.gather.ctrl;

import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.service.SourceService;
import com.wxmp.wxcms.ctrl.BaseCtrl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/gather/buy")
public class BuyCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(BuyCtrl.class);
	@Autowired
	SourceService sourceService;

	@RequestMapping(value = "/confirm.html")
	public ModelAndView confirmHtml(HttpServletRequest request,Integer type, String source){
		ModelAndView mv = new ModelAndView("gather/buy/confirm");
		log.info(">>>>>>>>>>>>>>>>>>> " + type + " | " + source);
		if (2 == type){
			RentSource s = sourceService.getSource(source);
			request.setAttribute("source",s);
		}
		return mv;
	}

}
