package cn.appsys.service.developer;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.appsys.dao.devuser.DevUserMapper;
import cn.appsys.entity.DevUser;

@Service
public class DevUserServiceImpl implements DevUserService {
	@Resource
	private DevUserMapper mapper;
	public DevUser login(String devCode, String devPassword) throws Exception{
		// TODO Auto-generated method stub
		DevUser user = null;
		user = mapper.getLoginUser(devCode);
		//匹配密码
		if(null != user){
			if(!user.getDevPassword().equals(devPassword))
				user = null;
		}
		return user;
	}
	public Integer register(DevUser devUser) {
		return mapper.register(devUser);
	}

	public DevUser checkDevCode(String devCode) {
		return mapper.checkDevCode(devCode);
	}
	public Integer updateDevUser(DevUser devuser) {
		// TODO Auto-generated method stub
		return mapper.updateDevUser(devuser);
	}
	public DevUser getDevUserById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getDevUserById(id);
	}

}
