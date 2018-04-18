package com.wxmp.gather.ctrl;

import com.wxmp.core.util.SessionUtil;
import com.wxmp.core.util.StringUtil;
import com.wxmp.gather.constant.GatherMessage;
import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.service.SourceService;
import com.wxmp.wxcms.ctrl.BaseCtrl;
import net.sf.json.JSON;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/gather/resource")
public class ResourceCtrl extends BaseCtrl{
	
	private static Logger log = LogManager.getLogger(ResourceCtrl.class);
	private String osName = System.getProperties().getProperty("os.name");
	/*
	 * 上传图片
	 */
	@RequestMapping("/file")
	@ResponseBody
	public JSON file(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		try {
			String realPath = osName.toLowerCase()
					.contains("linux") ? getPathByLinux(request)
					: getPathByWindows(request);// 文件的硬盘真实路径
			String filePath = upload(realPath, file);

			return getJsonResponse(true,GatherMessage.SUCCESS,GatherMessage.SUCCESS_MSG,"http://" + request.getServerName() + ":" + request.getServerPort() +"/" +request.getContextPath()+"res/images/" + filePath);
		} catch (Exception e) {
			e.printStackTrace();
			if (String.valueOf(GatherMessage.UPLOAD_FAIL).equals(e.getMessage())) {
				return getJsonResponse(false, GatherMessage.UPLOAD_FAIL, GatherMessage.UPLOAD_FAIL_NAME, null);
			}
			return getJsonResponse(false, GatherMessage.FAIL, GatherMessage.FAIL_NAME, null);
		}
	}

	public String upload(String path, MultipartFile file)
			throws Exception {
		if (file == null || file.getSize() == 0) {
			throw new Exception(String.valueOf(GatherMessage.UPLOAD_FAIL));
		}
		String fileExtName = file.getOriginalFilename();
		fileExtName = fileExtName.substring(fileExtName.lastIndexOf(".") + 1);
		String filename = StringUtil.getRandomLengthString(16) + "." + fileExtName;
		File tempFile = new File(path + filename);

		if (!tempFile.exists())
			tempFile.mkdirs();
		file.transferTo(tempFile);
		return tempFile.getName();
	}

	private String getPathByLinux(HttpServletRequest request) {
		String realContextPath = request.getServletContext().getRealPath("/");
		if (realContextPath.endsWith("/"))
			return realContextPath + "res/images/";
		else {
			return realContextPath + "/res/images/";
		}
	}

	private String getPathByWindows(HttpServletRequest request) {
		String realContextPath = request.getServletContext().getRealPath("/");
		if (realContextPath.endsWith("\\"))
			return realContextPath + "res\\images\\";
		else {
			return realContextPath + "\\res\\images\\";
		}
	}
}
