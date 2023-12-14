package com.hazr.personalblog.service;

import com.hazr.personalblog.dto.LoginResponseDTO;
import com.hazr.personalblog.dto.UserDTO;
import com.hazr.personalblog.exception.UsernameDoesNotExistException;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AzureBlobService azureBlobService;

    public UserService(UserRepository userRepository, AzureBlobService azureBlobService) {
        this.userRepository = userRepository;
        this.azureBlobService = azureBlobService;
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
}
