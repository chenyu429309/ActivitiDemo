package com.felix.activi.flow;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;
import org.junit.Test;


public class GroupUserTest {
	private Logger logger=Logger.getLogger(GroupUserTest.class);
	/**
	 * 获取默认的流程引擎实例，会自动加载active.cfg.xml
	 */
	private ProcessEngine processengine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void test() {
		logger.info("LOG");
		IdentityService identityService= processengine.getIdentityService();//获取身份service
		identityService.deleteUser("张三");
		User currentUser=identityService.createUserQuery().userId("张三").singleResult();
		assertNull(currentUser);
		identityService.deleteGroup("leaders");
		
		Group  group=identityService.newGroup("leaders");
		group.setName("计算机一班");
		group.setName("assignment");
		identityService.saveGroup(group);
		
		List<Group> groups=identityService.createGroupQuery().groupId("leaders").list();
		logger.info(groups.size());
	
		User user=identityService.newUser("张三");
		user.setEmail("chenyu429309@qq.com");
		user.setFirstName("Felix");
		user.setLastName("Wang");
		user.setPassword("qiang429309");
		identityService.saveUser(user);
		 currentUser=identityService.createUserQuery().userId("张三").singleResult();
		logger.info("当前用户"+currentUser);
		assertNotNull(currentUser);
		//跟据用户与组的id来创建之间的关系
		identityService.createMembership("张三", "leaders");
		
		currentUser=identityService.createUserQuery().memberOfGroup("leaders").singleResult();
		logger.info(currentUser);
		group=identityService.createGroupQuery().groupMember("张三").singleResult();
		logger.info(group);

	}

}
