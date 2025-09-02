package com.dashboard.Service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dashboard.Config.PasswordUtil;
import com.dashboard.DTO.LoginRequest;
import com.dashboard.Model.User;
import com.dashboard.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Kullanıcı bulunamadı"));
        }

        User user = userOpt.get();
        String hashedInputPassword = PasswordUtil.hashPassword(loginRequest.getPassword());

        if (!hashedInputPassword.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Hatalı şifre"));
        }

        String schema = "company" + user.getId();  

        return ResponseEntity.ok().body(Map.of(
            "message", "Login başarılı",
            "userId", user.getId(),
            "schema", schema,
            "hashedPassword", user.getPassword()
        ));
    }
}

