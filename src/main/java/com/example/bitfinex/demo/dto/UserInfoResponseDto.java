/**
 * 
 */
package com.example.bitfinex.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoResponseDto extends ResponseDto {

	private List<UserInfoDto> userInfoDtoList;

	public UserInfoResponseDto(int status, String message, List<UserInfoDto> userInfoDtoList) {
		super(status, message);
		this.userInfoDtoList = userInfoDtoList;
	}

}
