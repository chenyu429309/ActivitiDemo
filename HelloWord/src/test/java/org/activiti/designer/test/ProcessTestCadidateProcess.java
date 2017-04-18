package org.activiti.designer.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestCadidateProcess {

	private String filename = "D:\\gitDEmo\\HelloWord\\src\\main\\resources\\diagrams\\taskCandidateTest.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	private Logger logger=Logger.getLogger(ProcessTestCadidateProcess.class);
	
	@Test
	public void startProcess() throws Exception {
		
		
		//获取IdentityService
		IdentityService identityService=activitiRule.getIdentityService();
		identityService.deleteMembership("json", "公司领导");
		identityService.deleteMembership("felix", "公司领导");
		identityService.deleteGroup("公司领导");
		identityService.deleteUser("json");
		identityService.deleteUser("felix");
		TaskService taskService=activitiRule.getTaskService();
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("cadidateProcess.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		User json=identityService.newUser("json");
		User felix=identityService.newUser("felix");
		Group group=identityService.newGroup("公司领导");
		identityService.saveGroup(group);
		identityService.saveUser(felix);
		identityService.saveUser(json);
		
		identityService.createMembership(json.getId(), group.getId());
		identityService.createMembership(felix.getId(), group.getId());
		List<String> list=new ArrayList<String>();
		list.add(json.getId());
		list.add(felix.getId());
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		variableMap.put("users", list);
		variableMap.put("group", group.getId());//这里对应的是一个id不是具体的组
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("cadidateProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		Task jsonTask=taskService.createTaskQuery().taskCandidateUser(json.getId()).singleResult();
		logger.info("json的任务"+jsonTask);
		Task felixTask=taskService.createTaskQuery().taskCandidateUser(felix.getId()).singleResult();
		logger.info("felix的任务"+felixTask);
		
		//json签收任务
//		taskService.complete(felixTask.getId());不用指定人去完成
		taskService.claim(felixTask.getId(), json.getId());//指定某人去完成
		//felix再次查找任务
		felixTask=taskService.createTaskQuery().taskCandidateUser(felix.getId()).singleResult();
		logger.info(felixTask==null);
		
	}
}