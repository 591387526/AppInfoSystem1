package cn.appsys.service.developer;

import cn.appsys.entity.DevUser;

public interface DevUserService {
	/**
	 * 用户登录
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	public DevUser login(String devCode,String devPassword) throws Exception;
	
	/**
	 * 开发者账户注册
	 * @param devUser
	 * @return
	 */
	public Integer register(DevUser devUser);
	/**
	 * 校验devCode是否存在
	 * @param userCode
	 * @return
	 */
	public DevUser checkDevCode(String devCode);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Integer updateDevUser(DevUser devuser);
	/**
	 * 根据主键获取用户
	 * @param id
	 * @return
	 */
	public DevUser getDevUserById(Integer id);
}

