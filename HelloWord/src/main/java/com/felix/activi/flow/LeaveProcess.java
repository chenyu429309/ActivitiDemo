package com.felix.activi.flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class LeaveProcess {
	/**
	 * 获取默认的流程引擎实例，会自动加载active.cfg.xml
	 */
	private ProcessEngine processengine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * 部署流程定义 晨宇
	 */
	@Test
	public void deploy() {
		// 创建流程
		Deployment dep = processengine.getRepositoryService()// 获取部署Service
				.createDeployment()// 创建部署
				.addClasspathResource("diagrams/leave.bpmn")// 加载资源文件
				.addClasspathResource("diagrams/leave.png")// 加载资源文件
				.name("leave")// 流程名字
				.deploy();// 部署
		System.out.println(dep.getId());
		System.out.println(dep.getCategory());
		System.out.println(dep.getName());
		System.out.println(dep.getDeploymentTime());
		System.out.println(dep.getTenantId());
	}

	/**
	 * 启动流程实例 晨宇
	 */
	@Test
	public void start() {
		/**
		 * 开始流程到第一个节点
		 */
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("to","chenyu429309@163.com");
		variables.put("from","chenyu429309@163.com");
		variables.put("subject","chenyu429309@163.com");
		variables.put("charset","UTF-8");
		variables.put("cc","chenyu429309@163.com");
		variables.put("html","chenyu429309@163.com");
		ProcessInstance pi = processengine.getRuntimeService()// 获取运行时Service
				.startProcessInstanceByKey("myFirstTest",variables);// 根据KEY启动流程
		System.out.println(pi.getActivityId());
		System.out.println(pi.getBusinessKey());
		System.out.println(pi.getDeploymentId());
		System.out.println(pi.getDescription());
		System.out.println(pi.getId());
		System.out.println(pi.getName());
		System.out.println(pi.getParentId());
		System.out.println(pi.getProcessDefinitionId());
		System.out.println(pi.getProcessDefinitionKey());
		System.out.println(pi.getProcessDefinitionName());

	}

	/**
	 * 获取某人的待办事项 晨宇
	 */
	@Test
	public void findTask() {
		List<Task> taskList = processengine.getTaskService()// 获取任务Service
				.createTaskQuery()// 创建任务查询
				.taskAssignee("java1234_晓峰")// 任务委派人
				.list();// 获取委派人的事项列表
		for (Task task : taskList) {
			System.out.println(task.getId());//任务ID
			System.out.println(task.getName());//任务的名字
			System.out.println(task.getOwner());
			System.out.println(task.getParentTaskId());
			System.out.println(task.getTaskDefinitionKey());
			System.out.println(task.getTaskLocalVariables());
			System.out.println(task.getProcessVariables());
			System.out.println(task.getCreateTime());
		}
	}

	/**
	 * 完成任务 晨宇
	 */
	@Test
	public void completeTask() {
		processengine.getTaskService()//任务相关Service
					.complete("20004");//指定要完成的任务
	}
	//

}


