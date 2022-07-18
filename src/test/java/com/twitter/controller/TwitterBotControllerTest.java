package com.twitter.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.twitter.exception.QuoteException;
import com.twitter.exception.TwitterException;
import com.twitter.service.QuoteService;
import com.twitter.service.TwitterService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TwitterBotController.class)
class TwitterBotControllerTest {

	@MockBean
	private QuoteService quoteService;

	@MockBean
	TwitterService twitterService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGenerateQuoteTryBlock() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/generateQuote").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGenerateQuoteCatchBlock() throws Exception {
		Mockito.when(quoteService.getRandomQuote()).thenThrow(QuoteException.class);
		assertThrows(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				TwitterBotController twitterBotController = new TwitterBotController();
				twitterBotController.generateQuote();
				throw new QuoteException();
			}
		});
	}

	@Test
	void testTweetQuoteTryBlock() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/tweetQuote").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testTweetQuoteCatchBlock() throws Exception {
		Mockito.doNothing().when(twitterService).postTweet();
		assertThrows(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				TwitterBotController twitterBotController = new TwitterBotController();
				twitterBotController.tweetQuote();
				throw new TwitterException();
			}
		});
	}

}
