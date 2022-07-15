package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 15-Jul-2022
 */
@Service
@Slf4j
public class TwitterBotServiceImpl implements TwitterBotService {
	@Autowired
	private QuoteService quoteService;

	@Override
	public void postTweet() {
		try {
			log.info("calling postTweet service");
			quoteService.getRandomQuote();
		} catch (Exception e) {
		}
	}

}
