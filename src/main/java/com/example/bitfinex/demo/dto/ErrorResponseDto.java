/**
 * 
 */
package com.example.bitfinex.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResponseDto extends ResponseDto {
	private List<String> errors;

	public ErrorResponseDto(int status, String message) {
		super(status, message);
	}

	public ErrorResponseDto(int status, String message, List<String> errors) {
		super(status, message);
		this.errors = errors;
	}

}
