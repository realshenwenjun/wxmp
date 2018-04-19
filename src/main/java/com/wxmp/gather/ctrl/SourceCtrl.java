package com.wxmp.gather.ctrl;

import com.wxmp.core.util.QrcodeUtil;
import com.wxmp.core.util.SessionUtil;
import com.wxmp.core.util.StringUtil;
import com.wxmp.gather.constant.GatherMessage;
import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.domain.RentSourceUser;
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

	private String osName = System.getProperties().getProperty("os.name");

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
			String realPath = osName.toLowerCase()
					.contains("linux") ? getPathByLinux(request)
					: getPathByWindows(request);// 文件的硬盘真实路径
			String fileName = StringUtil.getRandomLengthString(16) + ".png";
			String path = realPath + fileName;
			QrcodeUtil.createEncode("source="+source.getId()+"&type=2",path);
			source.setQrcode("http://" + request.getServerName() + ":" + request.getServerPort() +request.getContextPath()+"/res/upload/" + fileName);

			sourceService.addSource(source, SessionUtil.getGatherUserId());
		}catch (Exception e){
			e.printStackTrace();
			if (String.valueOf(GatherMessage.SOURCE_END_LIMIT).equals(e.getMessage()))
				return getJsonResponse(false, GatherMessage.SOURCE_END_LIMIT,GatherMessage.SOURCE_END_LIMIT_NAME,null);
		}

		return getJsonResponse(true, GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,null);
	}

	private String getPathByLinux(HttpServletRequest request) {
		String realContextPath = request.getServletContext().getRealPath("/");
		if (realContextPath.endsWith("/"))
			return realContextPath + "res/upload/";
		else {
			return realContextPath + "/res/upload/";
		}
	}

	private String getPathByWindows(HttpServletRequest request) {
		String realContextPath = request.getServletContext().getRealPath("/");
		if (realContextPath.endsWith("\\"))
			return realContextPath + "res\\upload\\";
		else {
			return realContextPath + "\\res\\upload\\";
		}
	}

	@RequestMapping(value = "/detail.html")
	public ModelAndView detailHtml(HttpServletRequest request, String id){
		ModelAndView mv = new ModelAndView("gather/source/detail");
		RentSource source = sourceService.getSource(id);
		request.setAttribute("source", source);
		return mv;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public JSON del(HttpServletRequest request, String id) throws Exception{
		List<RentSourceUser> sourceUsers = sourceService.getSourceUser(id);
		if (sourceUsers != null && sourceUsers.size() > 0){
			return getJsonResponse(false, GatherMessage.SOURCE_HAS_USER,GatherMessage.SOURCE_HAS_USER_NAME,null);
		}
		sourceService.delSource(id);
		return getJsonResponse(true, GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,null);
	}
}
