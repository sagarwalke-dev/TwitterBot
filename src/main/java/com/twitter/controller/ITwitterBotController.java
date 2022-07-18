package com.twitter.controller;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ITwitterBotController {

	@ApiOperation("Generate random quote")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Quotes Generated Successfully Completed"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Bad Request"), })
	@GetMapping("/generateQuote")
	public String generateQuote();
	
}
