package cn.appsys.dao.appcategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.appsys.entity.AppCategory;

public interface AppCategoryMapper {
	
	public List<AppCategory> getAppCategoryListByParentId(@Param("parentId")Integer parentId)throws Exception;
}
