package com.twitter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.twitter.configuration.SchedulerConfig;
import com.twitter.service.QuoteService;
import com.twitter.service.TwitterBotService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TwitterBotScheduler implements ApplicationRunner {

	private TaskScheduler taskScheduler;
	private SchedulerConfig schedulerConfig;
	private TwitterBotService twitterBotService;
	
	@Autowired
	private QuoteService quoteService;

	@Autowired
	public TwitterBotScheduler(@Qualifier("botScheduler") TaskScheduler taskScheduler, SchedulerConfig schedulerConfig,
			TwitterBotService twitterBotService) {
		super();
		this.taskScheduler = taskScheduler;
		this.schedulerConfig = schedulerConfig;
		this.twitterBotService = twitterBotService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		taskScheduler.scheduleWithFixedDelay(() -> {
			try {
				String threadName=Thread.currentThread().getName();
				log.info("Twitter Bot scheduler stared {}",threadName);
//				twitterBotService.postTweet();
				quoteService.getRandomQuote();
				log.info("Twitter Bot scheduler execution completed {}",threadName);
			} catch (Exception e) {
				log.info("Exception while running scheduler {}", ExceptionUtils.getStackTrace(e));
			}
		}, schedulerConfig.getInterval());
	}

}
