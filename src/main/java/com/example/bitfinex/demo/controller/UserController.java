package com.example.bitfinex.demo.controller;

import com.example.bitfinex.demo.dto.ResponseDto;
import com.example.bitfinex.demo.dto.UserInfoDto;
import com.example.bitfinex.demo.dto.UserInfoResponseDto;
import com.example.bitfinex.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "UserInfo-v1", nickname = "User Info Version 1")
    @GetMapping(value = "", produces = {"application/vnd.demo.app-v1+json"})
    public ResponseEntity<ResponseDto> findUsersV1() {
        List<UserInfoDto> userInfoDtoList = userService.getBasicInfoUsers();

        return new ResponseEntity<ResponseDto>(
                new UserInfoResponseDto(HttpStatus.OK.value(), "Success", userInfoDtoList), HttpStatus.OK);
    }

    @ApiOperation(value = "UserInfo-v2", nickname = "User Info Version 2")
    @GetMapping(value = "", produces = {"application/vnd.demo.app-v2+json"})
    public ResponseEntity<ResponseDto> findUsersV2() {
        List<UserInfoDto> userInfoDtoList = userService.getDetailedInfoUsers();

        return new ResponseEntity<ResponseDto>(
                new UserInfoResponseDto(HttpStatus.OK.value(), "Success", userInfoDtoList), HttpStatus.OK);
    }

    @ApiOperation(value = "Search User", nickname = "Search User")
    @GetMapping(value = "/{name}")
    public ResponseEntity<ResponseDto> findUserByName(@PathVariable String name) {
        List<UserInfoDto> userInfoDtoList = userService.findUserByName(name);

        return new ResponseEntity<ResponseDto>(
                new UserInfoResponseDto(HttpStatus.OK.value(), "Success", userInfoDtoList), HttpStatus.OK);
    }
}
