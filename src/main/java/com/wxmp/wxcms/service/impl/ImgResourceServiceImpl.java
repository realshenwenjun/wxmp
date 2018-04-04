package com.wxmp.wxcms.service.impl;

import com.wxmp.backstage.common.ATTContents;
import com.wxmp.wxcms.domain.ImgResource;
import com.wxmp.wxcms.mapper.ImgResourceDao;
import com.wxmp.wxcms.service.ImgResourceService;
import com.wxmp.core.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

/** 
 * @title : 
 * @description : 
 * @projectname : wxmp
 * @classname : ImgResourceServiceImpl
 * @version 1.0
 * @author : hermit
 * @createtime : 2017年6月12日 上午4:47:35
*/
@Service
public class ImgResourceServiceImpl implements ImgResourceService {

	@Resource
    private ImgResourceDao imgResourceDao;
	
	
	@Override
	public ImgResource getImg(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addImg(ImgResource img) {
		img.setFlag(ATTContents.IMG_FLAG0);
		img.setCreateTime(new Date(System.currentTimeMillis()));
		img.setUpdateTime(img.getCreateTime());
		//主键id
		String id = CommonUtil.getUID();
		img.setId(id);
		imgResourceDao.add(img);
		return img.getUrl();
	}


	@Override
	public boolean removeOtherToImg(String otherId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateImgFlag(String id, Integer flag) {
		// TODO Auto-generated method stub
		return false;
	}

}
