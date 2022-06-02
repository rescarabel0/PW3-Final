package br.edu.aluno.projetofinal.user.service.auth;

import br.edu.aluno.projetofinal.user.domain.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService {
    @NonNull
    Optional<User> findById(@NonNull Long id);

    @NonNull
    Optional<User> findByLogin(@NonNull String login);
}
