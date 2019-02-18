package com.example.bitfinex.demo.repository;

import com.example.bitfinex.demo.domain.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {

    Optional<OauthUser> findByNameAndEmail(String name, String email);

    List<OauthUser> findByFirstNameOrLastName(String firstName, String lastName);
}
