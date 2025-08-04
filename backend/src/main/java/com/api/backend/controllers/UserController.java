package com.api.backend.controllers;

import com.api.backend.models.User;
import com.api.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET - Buscar dados do usuário autenticado
    @GetMapping("/me")
    public ResponseEntity<?> getMyUserData(Principal principal) {
        String login = principal.getName();
        Optional<User> user = userRepository.findByLogin(login);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

    // PUT - Atualizar dados (nome e email, por exemplo)
    @PutMapping("/me")
    public ResponseEntity<?> updateMyUserData(@RequestBody User updatedUserData, Principal principal) {
        String login = principal.getName();
        Optional<User> optionalUser = userRepository.findByLogin(login);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        user.setName(updatedUserData.getName());
        user.setEmail(updatedUserData.getEmail());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    // DELETE - Deletar conta
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteMyAccount(Principal principal) {
        String login = principal.getName();
        Optional<User> optionalUser = userRepository.findByLogin(login);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(optionalUser.get());
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
