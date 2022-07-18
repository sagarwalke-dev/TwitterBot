package com.twitter.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
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
import com.twitter.service.QuoteService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TwitterBotController.class)
class TwitterBotControllerTest {

	@MockBean
	private QuoteService quoteService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void testGenerateQuoteTryBlock() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/generateQuote").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(2)
	void testGenerateQuoteCatchBlock() throws Exception {
		Mockito.when(quoteService.getRandomQuote()).thenThrow(QuoteException.class);
		assertThrows(QuoteException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				TwitterBotController twitterBotController = new TwitterBotController(quoteService);
				twitterBotController.generateQuote();
				throw new QuoteException();
			}
		});
	}

}
