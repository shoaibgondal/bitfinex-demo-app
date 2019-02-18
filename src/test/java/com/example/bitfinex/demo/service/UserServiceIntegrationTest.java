package com.example.bitfinex.demo.service;

import com.example.bitfinex.demo.domain.OauthUser;
import com.example.bitfinex.demo.dto.UserInfoDto;
import com.example.bitfinex.demo.repository.OauthUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private OauthUserRepository userRepository;

    @Before
    public void setUp() {
        OauthUser shoaib = new OauthUser("Google", "Shoaib Ahmad Gondal", "Shoaib",
                "Ahmad Gondal", "kool.gondal@gmail.com");
        shoaib.setId(1L);

        Mockito.when(userRepository.findByFirstNameOrLastName(shoaib.getFirstName(), shoaib.getFirstName())).
                thenReturn(Arrays.asList(shoaib));
        Mockito.when(userRepository.findByFirstNameOrLastName("wrong_name", "wrong_name")).
                thenReturn(null);
        Mockito.when(userRepository.findByNameAndEmail(shoaib.getName(), shoaib.getEmail())).
                thenReturn(Optional.of(shoaib));
        Mockito.when(userRepository.findByNameAndEmail("wrong_name", "email"))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.findById(shoaib.getId())).thenReturn(Optional.of(shoaib));
        Mockito.when(userRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String firstName = "Shoaib";
        String lastName = "Ahmad Gondal";

        UserInfoDto found = userService.findUserByName(firstName).get(0);

        assertThat(found.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void whenInValidName_thenUserShouldNotBeFound() {
        List<UserInfoDto> fromDb = userService.findUserByName("wrong_name");

        assertThat(fromDb).isNull();
    }

}
