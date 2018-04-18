package com.wxmp.gather.ctrl;

import com.wxmp.core.util.SessionUtil;
import com.wxmp.core.util.StringUtil;
import com.wxmp.gather.constant.GatherMessage;
import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.service.SourceService;
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
import java.util.List;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/gather/source")
public class SourceCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(SourceCtrl.class);

	@Autowired
	SourceService sourceService;

	@RequestMapping(value = "/source.html")
	public ModelAndView source(HttpServletRequest request, RentSource source){
		ModelAndView mv = new ModelAndView("gather/source/source");
		List<RentSource> sources = sourceService.getManagerSource(SessionUtil.getGatherUserId(),source.getName());
		request.setAttribute("sources",sources);
		request.setAttribute("form",source);
		return mv;
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView addHtml(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("gather/source/add");
		return mv;
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public JSON add(HttpServletRequest request, RentSource source) throws Exception{
		source.setId(StringUtil.getRandomLengthString(32));
		source.setCreateTime(new Date());
		try {
			sourceService.addSource(source, SessionUtil.getGatherUserId());
		}catch (Exception e){
			if (String.valueOf(GatherMessage.SOURCE_END_LIMIT).equals(e.getMessage()))
				return getJsonResponse(false, GatherMessage.SOURCE_END_LIMIT,GatherMessage.SOURCE_END_LIMIT_NAME,null);
		}

		return getJsonResponse(true, GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,null);
	}
}
