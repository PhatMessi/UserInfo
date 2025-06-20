package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   
   @Autowired
   private UserRepository userRepository;
   
   public User saveUser(UserDTO userDTO) {
       // Convert DTO to Entity
       User user = new User();
       user.setHoTen(userDTO.getHoTen());
       user.setDiaChi(userDTO.getDiaChi());
       user.setSoDienThoai(userDTO.getSoDienThoai());
       user.setMoTa(userDTO.getMoTa());
       
       // Save to database
       return userRepository.save(user);
   }
   
   public List<User> getAllUsers() {
       return userRepository.findAll();
   }
   
   public User getUserById(Long id) {
       return userRepository.findById(id).orElse(null);
   }
   public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setHoTen(userDTO.getHoTen());
            user.setDiaChi(userDTO.getDiaChi());
            user.setSoDienThoai(userDTO.getSoDienThoai());
            user.setMoTa(userDTO.getMoTa());
            return userRepository.save(user);
        }
        return null;
}

public boolean deleteUser(Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        return true;
    }
    return false;
}
}