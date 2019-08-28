package cn.appsys.dao.devuser;

import org.apache.ibatis.annotations.Param;
import cn.appsys.entity.DevUser;

public interface DevUserMapper {
	/**
	 * 通过userCode获取User
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public DevUser getLoginUser(@Param("devCode")String devCode)throws Exception;
	
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
