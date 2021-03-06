package com.github.dactiv.showcase.dao.account;

import java.util.List;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.github.dactiv.showcase.entity.account.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源数据访问
 * 
 * @author maurice
 *
 */
@Repository
public class ResourceDao extends HibernateSupportDao<Resource, String>{

	/**
	 * 通过用户id获取用户所有资源
	 * 
	 * @param userId 用户id
	 * 
	 * @return List
	 */
	public List<Resource> getUserResources(String userId) {
		return distinct(Resource.UserResources, userId);
	}
	
	/**
	 * 刷新一次Resource的leaf字段，如果该leaf = 1 并且该资源没有子类，把该资源的leaf改成0
	 */
	public void refreshAllLeaf() {
		List<Resource> list = findByQuery(Resource.LeafTureNotAssociated);
		for (Resource entity : list) {
			entity.setLeaf(false);
			save(entity);
		}
		
	}

	
}
