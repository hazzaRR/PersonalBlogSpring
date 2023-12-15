package com.hazr.personalblog.service;

import com.hazr.personalblog.dto.UpdateUserDetailsDTO;
import com.hazr.personalblog.dto.UserDTO;
import com.hazr.personalblog.exception.EmailAlreadyTakenException;
import com.hazr.personalblog.exception.IncorrectPasswordException;
import com.hazr.personalblog.exception.UsernameAlreadyTakenException;
import com.hazr.personalblog.exception.UsernameDoesNotExistException;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AzureBlobService azureBlobService;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, AzureBlobService azureBlobService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.azureBlobService = azureBlobService;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username is not valid"));
    }

    public UserDTO getUserDetails(String username) {

        Optional<User> loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser.isEmpty()) {
            throw new UsernameDoesNotExistException("the username "+ username + " does not exist");
        }

        UserDTO userDetails;
        User user = loggedInUser.get();

        if (user.getProfilePicURL() != null)
        {
            userDetails = new UserDTO(username, user.getEmail(), user.getFirstname(), user.getSurname(), azureBlobService.getFile(user.getProfilePicURL()));
        } else {
            userDetails = new UserDTO(username, user.getEmail(), user.getFirstname(), user.getSurname());
        }

        return userDetails;
    }

    @Transactional
    public UserDTO updateUserDetails(String username, UpdateUserDetailsDTO userDetails, MultipartFile profilePicture) throws IOException {

            Optional<User> user = userRepository.findByUsername(username);

            if (user.isEmpty()) {
                throw new UsernameDoesNotExistException("the username " + username + " does not exist");
            }


            User loggedInUser = user.get();

            if(!passwordEncoder.matches(userDetails.getPassword(), loggedInUser.getPassword())) {
                throw new IncorrectPasswordException("password provided is incorrect");
            }

            if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty() && !Objects.equals(userDetails.getUsername(), loggedInUser.getUsername())) {
                Optional<User> usernameAlreadyExists = userRepository.findByUsername(userDetails.getUsername());
                if (usernameAlreadyExists.isPresent()) {
                    throw new UsernameAlreadyTakenException("the username " + username + " is already taken");
                }
                loggedInUser.setUsername(userDetails.getUsername());
            }

            if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty() && !Objects.equals(userDetails.getEmail(), loggedInUser.getEmail())) {

                Optional<User> emailAlreadyExists = userRepository.findByEmail(userDetails.getEmail());

                if (emailAlreadyExists.isPresent()) {
                    throw new EmailAlreadyTakenException("the email " + userDetails.getEmail() + " is already taken");
                }
                loggedInUser.setEmail(userDetails.getEmail());
            }

            if (userDetails.getFirstname() != null && !userDetails.getFirstname().isEmpty() && !Objects.equals(userDetails.getFirstname(), loggedInUser.getFirstname())) {

                loggedInUser.setFirstname(userDetails.getFirstname());
            }

            if (userDetails.getSurname() != null && !userDetails.getSurname().isEmpty() && !Objects.equals(userDetails.getSurname(), loggedInUser.getSurname())) {

                loggedInUser.setSurname(userDetails.getSurname());
            }


            if (profilePicture != null) {
                String fileName = azureBlobService.upload(loggedInUser.getUsername() + "_profilePicture", profilePicture);

                if (loggedInUser.getProfilePicURL() == null) {
                    loggedInUser.setProfilePicURL(fileName);
                }

            }

            return this.getUserDetails(loggedInUser.getUsername());

    }

}
