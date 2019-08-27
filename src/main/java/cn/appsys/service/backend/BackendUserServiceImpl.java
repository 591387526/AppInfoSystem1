package cn.appsys.service.backend;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.backenduser.BackendUserMapper;
import cn.appsys.entity.BackendUser;

@Service("backendService")
@Transactional
public class BackendUserServiceImpl implements BackendUserService {
	@Resource
	private BackendUserMapper backendmapper;
	
	public BackendUser login(String userCode, String userPassword) throws Exception {
		BackendUser user = null;
		user = backendmapper.getLoginUser(userCode);
		//匹配密码
		if(null != user){
			if(!user.getUserPassword().equals(userPassword))
				user = null;
		}
		return user;
	}

}
