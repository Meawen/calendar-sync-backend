package com.calendarsyncbackend.services;

import com.calendarsyncbackend.models.User;
import com.calendarsyncbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    public Page<User> findAllUsersPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(int id,User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole(userDetails.getRole());
            user.setName(userDetails.getName());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        }
        return null;
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
