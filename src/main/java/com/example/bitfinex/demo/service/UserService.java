package com.example.bitfinex.demo.service;

import com.example.bitfinex.demo.domain.OauthUser;
import com.example.bitfinex.demo.dto.UserInfoDto;
import com.example.bitfinex.demo.repository.OauthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class, contains operations related to User.
 */
@Service
public class UserService {

    @Autowired
    private OauthUserRepository oauthUserRepository;

    /**
     * Save new user record OR update if existing.
     *
     * @param userInfoDto
     */
    public void saveOrUpdateUser(UserInfoDto userInfoDto) {
        OauthUser oauthUser = null;

        Optional<OauthUser> oauthUserOptional = oauthUserRepository.findByNameAndEmail(userInfoDto.getName(), userInfoDto.getEmail());
        if (oauthUserOptional.isPresent()) {    // existing user
            oauthUser = oauthUserOptional.get();
            oauthUser.setLastLoginAt(new Date());
            oauthUserRepository.save(oauthUser);

        } else {    // new user
            oauthUser = new OauthUser(userInfoDto.getClient(), userInfoDto.getName(),
                    userInfoDto.getFirstName(), userInfoDto.getLastName(), userInfoDto.getEmail());
            oauthUser.setLastLoginAt(new Date());
            oauthUserRepository.save(oauthUser);
        }
    }

    /**
     * Returns Basic Info of Users (first name, last name)
     *
     * @return
     */
    public List<UserInfoDto> getBasicInfoUsers() {
        List<OauthUser> userList = oauthUserRepository.findAll();
        return userList.stream().map(u -> new UserInfoDto(u.getFirstName(), u.getLastName())).collect(Collectors.toList());
    }

    /**
     * Returns Detailed Info of Users (first name, last name, email, signup date)
     *
     * @return
     */
    public List<UserInfoDto> getDetailedInfoUsers() {
        List<OauthUser> userList = oauthUserRepository.findAll();
        return userList.stream().map(u ->
                new UserInfoDto(u.getFirstName(), u.getLastName(), u.getEmail(), u.getCreatedAt()))
                .collect(Collectors.toList());
    }

    /**
     * Finds user with first name OR last name for provided name value.
     *
     * @param name
     * @return
     */
    public List<UserInfoDto> findUserByName(String name) {

        List<OauthUser> userList = oauthUserRepository.findByFirstNameOrLastName(name, name);

        if (userList != null) {
            return userList.stream().map(u ->
                    new UserInfoDto(u.getFirstName(), u.getLastName(), u.getEmail(), u.getCreatedAt())).
                    collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
