package com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class TwitterBotApplication{

	public static void main(String[] args) {
		SpringApplication.run(TwitterBotApplication.class, args);
		log.info("Twitter bot application started");
	}

}
