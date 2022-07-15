/**
 * 
 */
package com.twitter.entity;

import java.util.List;

import lombok.Data;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 15-Jul-2022
 */
@Data
public class Quotes {
	private Integer status;
	private String message;
	private String count;
	private List<QuoteData> quotes;
}
