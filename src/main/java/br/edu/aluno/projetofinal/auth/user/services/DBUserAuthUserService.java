package br.edu.aluno.projetofinal.auth.user.services;

import br.edu.aluno.projetofinal.auth.user.dtos.AuthUserDTO;
import br.edu.aluno.projetofinal.user.service.auth.UserService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserAuthUserService implements UserAuthService {
    @NonNull
    private final UserService userService;

    public DBUserAuthUserService(@NonNull UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<AuthUserDTO> findUserByCredentials(@NonNull String login, @Nullable String password) {
        var optionalFoundUser = userService.findByLogin(login);
        if (optionalFoundUser.isEmpty()) return Optional.empty();

        var user = optionalFoundUser.map(
                u -> new AuthUserDTO(u.getLogin(), u.getPassword())
        ).get();
        return Optional.ofNullable(
                password == null || checkPassword(user, password) ? user : null
        );
    }

    private boolean checkPassword(@NonNull AuthUserDTO user, @NonNull String password) {
        return user.getPassword() != null && user.getPassword().equals(password);
    }
}
