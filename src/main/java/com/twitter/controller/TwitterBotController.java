package com.twitter.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.exception.QuoteException;
import com.twitter.service.QuoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class TwitterBotController implements ITwitterBotController {

	private QuoteService quoteService;

	@Autowired
	public TwitterBotController(QuoteService quoteService) {
		super();
		this.quoteService = quoteService;
	}

	@Override
	public String generateQuote() {
		try {
			log.info("calling generateQuote from controller");
			return quoteService.getRandomQuote();
		} catch (QuoteException e) {
			log.error("Error in generateQuote controller: {}", ExceptionUtils.getStackTrace(e));
			return e.getMessage();
		}
	}

}
