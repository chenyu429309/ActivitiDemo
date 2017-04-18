package com.felix.activi.table;

import static org.junit.Assert.*;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestActividemo {

	@Test
	public void test() {
		ProcessEngineConfiguration pre=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processengine=pre.buildProcessEngine();
	}

}
