package br.edu.aluno.projetofinal.user.controller;

import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.dtos.UserDTO;
import br.edu.aluno.projetofinal.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @NonNull
    private final UserService userService;

    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/{id}/", "/{id}"})
    @NonNull
    public ResponseEntity<User> findOne(@NonNull @PathVariable Long id) {
        try {
            var foundUser = userService.findById(id);
            if (foundUser.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(foundUser.get());
        } catch (EntityNotFoundException ignored) {
            return ResponseEntity.notFound().build();
        }
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

    @PutMapping({"/{id}", "/{id}/"})
    @NonNull
    public ResponseEntity<User> update(@NonNull @RequestBody UserDTO body, @NonNull @PathVariable Long id) {
        try {

            var updatedUser = userService.update(id, body.toDomain());
            if (updatedUser.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(updatedUser.get());
        } catch (EntityNotFoundException ignored) {
            return ResponseEntity.notFound().build();
        } catch (Throwable ignored) {
        }
        return ResponseEntity.badRequest().build();
    }
}
