package com.daifaming.restfulservice.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import com.daifaming.commons.date.constants.PatternConstant;
//import com.daifaming.commons.date.utils.DateUtils;

@Service("testTask")
public class TestTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

	@Value("${quartz.testJob.flag}")
	private boolean flag;

	public void execute() {
		if (flag) {
			LOGGER.info("cron job success!");
//			LOGGER.info("now is {}", DateUtils.getLocalTimeStr(PatternConstant.yyyy_NN_dd_HH_mm_ss));
		}
	}
}
