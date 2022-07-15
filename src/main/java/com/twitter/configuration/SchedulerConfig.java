package com.twitter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "scheduler")
@Data
public class SchedulerConfig {

	private Long interval;

	@Bean(name = "botScheduler")
	@Primary
	public TaskScheduler scheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(1);
		return taskScheduler;
	}
}