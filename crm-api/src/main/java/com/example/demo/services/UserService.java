package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;

//    @Autowired
//    JwtUtil jwtUtil;

    public String registerUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setMobile(user.getMobile());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        userRepo.save(newUser);

        return user.getName() + " Registered Successfully!! ";
    }

    public ResponseEntity<?> authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Create a success response object with user info
            Map<String, Object> userInfo = Map.of(
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "mobile", user.getMobile(),
                    "username", user.getUsername()
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Login Successful", "userInfo", userInfo));
        } else {
            // Create an error response object
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid details"));
        }
    }

//     public ResponseEntity<?> authenticateUser(String username, String password) {
//         User user = userRepo.findByUsername(username);
//         if (user != null && user.getPassword().equals(password)) {
//             // Create a success response object
//             return ResponseEntity.status(HttpStatus.OK)
//                     .body(Map.of("message", "Successfully logged in"));
//         } else {
//             // Create an error response object
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body(Map.of("error", "Invalid details"));
//         }
//     }

//    public ResponseEntity<?> authenticateUser(String username, String password) {
//    User user = userRepo.findByUsername(username);
//    if (user != null && user.getPassword().equals(password)) {
//        String token = jwtUtil.generateToken(username);
//        return ResponseEntity.ok(Map.of("token", token));
//    } else {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid details"));
//    }
//    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public ResponseEntity<?> getUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
        }
    }

    public ResponseEntity<?> updateUser(Long id, User userDetails) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setMobile(userDetails.getMobile());
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            userRepo.save(user);
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
