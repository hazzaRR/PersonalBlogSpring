package com.hazr.personalblog.service;

import com.hazr.personalblog.dto.LoginResponseDTO;
import com.hazr.personalblog.model.Role;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.RoleRepository;
import com.hazr.personalblog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User registerUser(String username, String password, String firstname, String surname, String email, String profilePicURL) {

        String encryptedPassword =  passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("ROLE_READER").get();

        Set<Role> authorities = new HashSet<>();


        authorities.add(userRole);


        return userRepository.save(new User(username, encryptedPassword, authorities, firstname, surname, email, null));

    }

    public LoginResponseDTO loginUser(String username, String password) throws LoginException {

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            List<String> roles = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

//            System.out.println(scope);

//            List<Role> userRoles = userRepository.findUserRoles(username);

            return new LoginResponseDTO(username, token, roles);

        } catch(AuthenticationException e) {
            System.out.println(e);

            throw new LoginException("incorrect login details");
        }
    }

    public User registerAuthor(String username, String password, String firstname, String surname, String email, String profilePicURL) {
        String encryptedPassword =  passwordEncoder.encode(password);

        Role roleReader = roleRepository.findByAuthority("ROLE_READER").get();
        Role roleAuthor = roleRepository.findByAuthority("ROLE_AUTHOR").get();

        Set<Role> authorities = new HashSet<>(List.of(new Role[]{roleReader, roleAuthor}));

        return userRepository.save(new User(username, encryptedPassword, authorities, firstname, surname, email, null));
    }
}
