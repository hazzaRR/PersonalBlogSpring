package com.hazr.personalblog.repository;


import com.hazr.personalblog.model.Role;
import com.hazr.personalblog.model.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        Role author = new Role("ROLE_AUTHOR");
        Role admin = new Role("ROLE_ADMIN");
        Role reader = new Role("ROLE_READER");

        roleRepository.saveAll(List.of(admin, author, reader));
        List<User> users = List.of(new User[]{
                new User("harryredman", "password", new HashSet<Role>(List.of(author, reader)), "Harry", "Redman", "harryredman@email.com", null),
                new User("admin1", "password2", new HashSet<Role>(List.of(admin, author, reader)), "admin", "admin", "admin@email.com", null),
        });

        userRepository.saveAll(users);
    }


    @Test
    void FindByUsername() {
        Optional<User> result = userRepository.findByUsername("harryredman");

        assertThat(result).isNotEmpty();
    }
}
