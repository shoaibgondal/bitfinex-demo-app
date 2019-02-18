/**
 * 
 */
package com.example.bitfinex.demo.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private int status;
	private String message;

	public ResponseDto(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseDto() {
		super();
	}

}
