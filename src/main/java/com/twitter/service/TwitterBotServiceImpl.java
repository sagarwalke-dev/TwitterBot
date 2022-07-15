package com.twitter.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 15-Jul-2022
 */
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
