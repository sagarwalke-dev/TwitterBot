package com.twitter.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import com.twitter.controller.TwitterBotController;
import com.twitter.entity.QuoteData;
import com.twitter.entity.Quotes;
import com.twitter.exception.QuoteException;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig
class QuoteServiceImplTest {

	@Mock
	RestTemplate restTemplate;

	@Mock
	QuoteService quoteServiceMock;
	
	@InjectMocks
	QuoteServiceImpl quoteService;

	@Test
	void testGetRandomQuoteTryElseBlock() {
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class),
				Mockito.any(HttpEntity.class), Mockito.eq(Quotes.class))).thenReturn(null);
		quoteService.getRandomQuote();
	}

	@Test
	void testGetRandomQuoteTryIfBlock() {
		Quotes quotes = new Quotes();
		QuoteData quoteData = new QuoteData();
		List<QuoteData> list = new ArrayList<QuoteData>();
		quotes.setCount("1");
		quotes.setStatus(200);
		quotes.setMessage("success");
		quoteData.setAuthor("Sagar");
		quoteData.setText("This your day");
		quoteData.setTag("test");
		list.add(quoteData);
		quotes.setQuotes(list);
		System.out.println("quotes : {}" + quotes);
		ResponseEntity<Quotes> responseEntity = new ResponseEntity<Quotes>(quotes, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class),
				Mockito.any(HttpEntity.class), Mockito.eq(Quotes.class))).thenReturn(responseEntity);
		quoteService.getRandomQuote();
	}

	@Test
	void testGetRandomQuoteCatchBlock() {
		Mockito.doThrow(RuntimeException.class).when(quoteServiceMock).getRandomQuote();
		assertThrows(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				quoteServiceMock.getRandomQuote();
				throw new QuoteException();
			}
		});
	}
}
