package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
   
   @Autowired
   private UserService userService;
   
   // API nhận data từ form (POST)
   @PostMapping("/create")
   public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, 
                                     BindingResult bindingResult) {
       
       // Kiểm tra validation errors
       if (bindingResult.hasErrors()) {
           Map<String, String> errors = new HashMap<>();
           bindingResult.getFieldErrors().forEach(error -> 
               errors.put(error.getField(), error.getDefaultMessage())
           );
           return ResponseEntity.badRequest().body(errors);
       }
       
       try {
           User savedUser = userService.saveUser(userDTO);
           
           Map<String, Object> response = new HashMap<>();
           response.put("success", true);
           response.put("message", "Tạo user thành công!");
           response.put("data", savedUser);
           
           return ResponseEntity.ok(response);
           
       } catch (Exception e) {
           Map<String, Object> errorResponse = new HashMap<>();
           errorResponse.put("success", false);
           errorResponse.put("message", "Lỗi khi tạo user: " + e.getMessage());
           
           return ResponseEntity.badRequest().body(errorResponse);
       }
   }
   
   // API lấy tất cả users (GET)
   @GetMapping("/all")
   public ResponseEntity<List<User>> getAllUsers() {
       List<User> users = userService.getAllUsers();
       return ResponseEntity.ok(users);
   }
   
   // API lấy user theo ID (GET)
   @GetMapping("/{id}")
   public ResponseEntity<?> getUserById(@PathVariable Long id) {
       User user = userService.getUserById(id);
       if (user != null) {
           return ResponseEntity.ok(user);
       } else {
           return ResponseEntity.notFound().build();
       }
   }
   // API cập nhật user (PUT)
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, 
        @Valid @RequestBody UserDTO userDTO, 
        BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        
        try {
            User updatedUser = userService.updateUser(id, userDTO);
            if (updatedUser != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Cập nhật user thành công!");
                response.put("data", updatedUser);
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Không tìm thấy user với ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Lỗi khi cập nhật user: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // API xóa user (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Xóa user thành công!");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Không tìm thấy user với ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Lỗi khi xóa user: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}

