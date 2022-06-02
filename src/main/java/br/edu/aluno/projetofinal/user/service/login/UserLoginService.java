package br.edu.aluno.projetofinal.user.service.login;

import br.edu.aluno.projetofinal.auth.token.models.Token;
import org.springframework.lang.NonNull;

public interface UserLoginService {
    @NonNull
    Token login(@NonNull String login, @NonNull String password);
}
