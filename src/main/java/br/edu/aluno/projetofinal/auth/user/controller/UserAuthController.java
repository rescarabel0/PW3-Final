package br.edu.aluno.projetofinal.auth.user.controller;

import br.edu.aluno.projetofinal.auth.user.dtos.UserAuthRequestDTO;
import br.edu.aluno.projetofinal.auth.user.dtos.UserAuthResponseDTO;
import br.edu.aluno.projetofinal.auth.user.services.login.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth/sign-in", "/auth/sign-in/"})
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class UserAuthController {
    @NonNull
    private final UserLoginService userLoginService;

    @PostMapping
    @NonNull
    public ResponseEntity<UserAuthResponseDTO> signIn(@NonNull @RequestBody UserAuthRequestDTO body) {
        try {
            var login = body.getLogin();
            var password = body.getPassword();
            if (login == null || password == null) {
                return ResponseEntity.badRequest().build();
            }
            var response = userLoginService.login(login, password);
            return ResponseEntity.ok(new UserAuthResponseDTO(response.getValue(), response.getExpireDate().getTime()));
        } catch (Throwable ignored) {
            return ResponseEntity.notFound().build();
        }
    }
}
