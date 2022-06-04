package br.edu.aluno.projetofinal.user.controller;

import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.dtos.UserDTO;
import br.edu.aluno.projetofinal.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @NonNull
    private final UserService userService;

    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/sign-up", "/sign-up/"})
    @NonNull
    public ResponseEntity<User> signUp(@NonNull @RequestBody UserDTO body) {
        try {
            var savedUser = this.userService.save(body.toDomain());
            if (savedUser.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(savedUser.get());
        } catch (EntityExistsException ignored) {
            return ResponseEntity.badRequest().build();
        }
    }
}
