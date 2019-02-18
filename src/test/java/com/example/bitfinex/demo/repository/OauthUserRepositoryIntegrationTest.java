package com.example.bitfinex.demo.repository;

import com.example.bitfinex.demo.domain.OauthUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OauthUserRepositoryIntegrationTest {

    @Autowired
    private OauthUserRepository userRepository;

    @Test
    public void givenUserNotInDBWhenFindByNameAndEmailThenReturnEmptyOptional() {
        Optional<OauthUser> foundUser = userRepository.findByNameAndEmail("Abc", "aa@bb.cc");

        assertThat(foundUser.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenUserInDBWhenFindByNameAndEmailThenReturnOptionalWithUser() {
        Optional<OauthUser> foundUser = userRepository.findByNameAndEmail("Shoaib Ahmad Gondal", "kool.gondal@gmail.com");

        assertThat(foundUser.isPresent()).isEqualTo(true);

        assertThat(foundUser
                .get()
                .getName()).isEqualTo("Shoaib Ahmad Gondal");

    }
}
