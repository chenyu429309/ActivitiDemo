package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestMyFirstTest {

	private String filename = "D:\\workspace\\HelloWord\\src\\main\\resources\\diagrams\\HelloWord.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("myFirstTest.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		variableMap.put("to","chenyu429309@163.com");
		variableMap.put("from","chenyu429309@163.com");
		variableMap.put("subject","chenyu429309@163.com");
		variableMap.put("charset","UTF-8");
		variableMap.put("cc","chenyu429309@163.com");
		variableMap.put("html","chenyu429309@163.com");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myFirstTest", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
}