package br.edu.aluno.projetofinal.auth.user.services.auth;

import br.edu.aluno.projetofinal.auth.user.dtos.AuthUserDTO;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserAuthService {
    @NonNull
    Optional<AuthUserDTO> findUserByCredentials(@NonNull String login, @Nullable String password);
}
