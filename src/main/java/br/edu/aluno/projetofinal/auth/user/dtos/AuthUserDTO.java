package br.edu.aluno.projetofinal.auth.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class AuthUserDTO {
    @Nullable
    private final String login;
    @Nullable
    private final String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserDTO that = (AuthUserDTO) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
