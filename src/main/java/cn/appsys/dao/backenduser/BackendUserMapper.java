package cn.appsys.dao.backenduser;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.appsys.entity.BackendUser;
@Repository("backendmapper")
public interface BackendUserMapper {
	
	/**
	 * 通过userCode获取User
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public BackendUser getLoginUser(@Param("userCode")String userCode) throws Exception;

}
