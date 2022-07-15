package com.twitter.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TwitterBotServiceImpl implements TwitterBotService{

	@Override
	public void postTweet() {
		try {
			log.info("calling postTweet service");
		} catch (Exception e) {
		}
	}

}
