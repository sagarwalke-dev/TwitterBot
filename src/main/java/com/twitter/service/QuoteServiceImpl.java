package com.twitter.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.twitter.entity.Quotes;
import com.twitter.exception.QuoteException;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 15-Jul-2022
 */
@Service
@Slf4j
@NoArgsConstructor
public class QuoteServiceImpl implements QuoteService {

	private RestTemplate restTemplate;

	@Value("${quotes.url}")
	private String quoteUrl;

	/**
	 * @param restTemplate
	 */
	@Autowired
	public QuoteServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Get the random quote from service
	 */
	@Override
	public String getRandomQuote() {
		Quotes response = null;
		Quotes quote = null;
		HttpEntity<String> httpEntity = null;
		try {
			log.info("caaling getRandomQuote");
			httpEntity = new HttpEntity<>(StringUtils.EMPTY);
			return restTemplate.exchange(quoteUrl, HttpMethod.GET, httpEntity, Quotes.class).getBody().getQuotes()
					.get(0).getText();
		} catch (QuoteException e) {
			log.error("Error while getting random quote: {}", ExceptionUtils.getStackTrace(e));
			throw new QuoteException();
		}
	}

}
