package com.twitter.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.twitter.QuoteException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuoteServiceImpl implements QuoteService {

	@Override
	public String getRandomQuote() {
		try {
			
		} catch (QuoteException e) {
			log.error("Error while getting random quote: {}", ExceptionUtils.getStackTrace(e));
			throw new QuoteException(e);
		}
		return null;
	}

}
