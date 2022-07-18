package com.twitter.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.exception.QuoteException;
import com.twitter.exception.TwitterException;
import com.twitter.service.QuoteService;
import com.twitter.service.TwitterService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@NoArgsConstructor
public class TwitterBotController implements ITwitterBotController {

	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private TwitterService twitterService;

	@Autowired
	public TwitterBotController(QuoteService quoteService, TwitterService twitterService) {
		super();
		this.quoteService = quoteService;
		this.twitterService = twitterService;
	}

	@Override
	public String generateQuote() {
		try {
			log.info("calling generateQuote from controller");
			return quoteService.getRandomQuote();
		} catch (Exception e) {
			log.error("Error in generateQuote controller: {}", ExceptionUtils.getStackTrace(e));
			return e.getMessage();
		}
	}

	@Override
	public String tweetQuote() {
		try {
			log.info("calling tweetQuote from controller");
			twitterService.postTweet();
			return null;
		} catch (Exception e) {
			log.error("Error in tweetQuote controller: {}", ExceptionUtils.getStackTrace(e));
			return e.getMessage();
		}
	}

}
