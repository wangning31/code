package com.zhangmin.restfulservice.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class TestTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);


	public void execute() {
	
			LOGGER.info("cron job success!");
	}
}
