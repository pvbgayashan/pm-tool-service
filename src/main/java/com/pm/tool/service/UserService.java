package com.pm.tool.service;

import com.pm.tool.dto.LoggedUser;
import com.pm.tool.dto.LoginUser;
import com.pm.tool.entity.User;
import com.pm.tool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public LoggedUser authenticate(LoginUser loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            LoggedUser loggedUser = new LoggedUser();
            loggedUser.setUserId(user.getId());
            loggedUser.setUserType(user.getUserType());
            loggedUser.setUserName(user.getUserName());
            loggedUser.setStatus(user.getStatus());
            return loggedUser;
        }
        return null;
    }

}
