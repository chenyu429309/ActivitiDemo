package com.felix.activi.table;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ActiviTest {

	@Test
	public void test() {
		ProcessEngineConfiguration pec=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		 pec.setJdbcDriver("com.mysql.jdbc.Driver");
	        pec.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
	        pec.setJdbcUsername("root");
	        pec.setJdbcPassword("");
	        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	        //获取流程引擎
	        ProcessEngine processengine=pec.buildProcessEngine();
	      /**
	       * 25张表的介绍
	        ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。

			ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
			
			ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
			
			ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
			
			ACT_GE_*: 'GE'表示general。通用数据， 用于不同场景下，如存放资源文件。	
	         
	         
	       */
	        
	}

}
