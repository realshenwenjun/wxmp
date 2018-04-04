package com.wxmp.wxcms.service.impl;

import com.wxmp.wxcms.domain.SysUser;
import com.wxmp.wxcms.mapper.SysUserDao;
import com.wxmp.wxcms.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 
 * @title : 
 * @description : 
 * @projectname : wxmp
 * @classname : SysUserServiceImpl
 * @version 1.0
 * @author : sw_jee_dev
 * @createtime : 2017年4月2日 上午10:05:11
 */

@Service
public class SysUserServiceImpl  implements ISysUserService{

	@Resource
	private SysUserDao sysUserDao;
	
	
	
	@Override
	public boolean isAccount(String account) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public SysUser getSysUser(SysUser sysUser) {
		SysUser resUser = null;
		
		resUser = this.sysUserDao.getSysUser(sysUser);

		if(resUser != null){
			return resUser;
		}else{
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see com.wxmp.backstage.sys.ISysUserService#getSysUserById(java.lang.String)
	 */
	@Override
	public SysUser getSysUserById(String userId) {
		SysUser resUser = null;
		
		resUser = this.sysUserDao.getSysUserById(userId);

		if(resUser != null){
			return resUser;
		}else{
			return null;
		}
	}


	@Override
	public int updateLoginPwd(SysUser sysUser) {
		int n = 0;
		try {
			sysUserDao.updateLoginPwd(sysUser);
			n = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}

}
