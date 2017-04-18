package com.felix.activi.flow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class ThreeReadFileTypeTest {
	private Logger logger = Logger.getLogger(ThreeReadFileTypeTest.class);
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	private final String bpmbpath = "diagrams/taskCandidateTest.bpmn";
	private final String pngpath = "diagrams/taskCandidateTest.png";
	private final String zippath = "diagrams/taskcadidateTest.zip";
	private final String filename = "D:\\gitDEmo\\HelloWord\\src\\main\\resources\\diagrams\\taskCandidateTest.bpmn";
	@Test
	public void ThreeReadFileTypeTestone() {
		// 部署
		processEngine.getRepositoryService()// 获取service
				.createDeployment()// 部署构建器
				.addClasspathResource(bpmbpath)// 加载资源
				.addClasspathResource(pngpath)// 加载资源
				.deploy();// 执行部署

		// 查询部署
		long count = processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.count();
		logger.info("有" + count + "条记录");

		// 查询图片
		logger.info("图片" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getDiagramResourceName());
		// 查询文件
		logger.info("文件" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getResourceName());

	}
	
	@Test
	public void ThreeReadFileTypeTesttwo() {
		
		InputStream zipin= this.getClass().getClassLoader().getResourceAsStream(zippath);
		// 部署
		processEngine.getRepositoryService()// 获取service
				.createDeployment()// 部署构建器
//				.addClasspathResource(bpmbpath)// 加载资源
//				.addClasspathResource(pngpath)// 加载资源
				.addZipInputStream(new ZipInputStream(zipin))
				.deploy();// 执行部署

		// 查询部署
		long count = processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.count();
		logger.info("有" + count + "条记录");

		// 查询图片
		logger.info("图片" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getDiagramResourceName());
		// 查询文件
		logger.info("文件" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getResourceName());

	}
	
	@Test
	public void ThreeReadFileTypeTestthree() {
		
//		InputStream zipin= this.getClass().getClassLoader().getResourceAsStream(zippath);
		FileInputStream fileInputStrem=null;
		try {
			fileInputStrem = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 部署
		processEngine.getRepositoryService()// 获取service
				.createDeployment()// 部署构建器
//				.addClasspathResource(bpmbpath)// 加载资源
//				.addClasspathResource(pngpath)// 加载资源
//				.addZipInputStream(new ZipInputStream(zipin))
				.addInputStream("taskCandidateTest.bpmn", fileInputStrem)
				.deploy();// 执行部署

		// 查询部署
		long count = processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.count();
		logger.info("有" + count + "条记录");

		// 查询图片
		logger.info("图片" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getDiagramResourceName());
		// 查询文件
		logger.info("文件" + processEngine.getRepositoryService()// 获取service
				.createProcessDefinitionQuery()// 查询存在的部署
				.processDefinitionKey("cadidateProcess")// 指定对应key
				.latestVersion()//
				.singleResult()//
				.getResourceName());

	}
	
	

}
