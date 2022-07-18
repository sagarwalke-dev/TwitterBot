package com.twitter.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.exception.QuoteException;
import com.twitter.exception.TwitterException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 15-Jul-2022
 */
@Service
@Slf4j
public class TwitterServiceImpl implements TwitterService {
	@Autowired
	private QuoteService quoteService;

	@Override
	public void postTweet() {
		try {
			log.info("calling postTweet service");
			quoteService.getRandomQuote();
		} catch (TwitterException e) {
			log.error("Error while posting tweet: {}", ExceptionUtils.getStackTrace(e));
			throw new TwitterException();
		}
	}

}
