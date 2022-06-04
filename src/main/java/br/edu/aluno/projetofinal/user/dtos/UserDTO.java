package br.edu.aluno.projetofinal.user.dtos;

import br.edu.aluno.projetofinal.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {
    @NonNull
    private String name;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private Time routineStartsAt;

    @NonNull
    private Time routineEndsAt;

    @NonNull
    public User toDomain() {
        return new User(
                null,
                this.name,
                this.login,
                this.password,
                this.routineStartsAt,
                this.routineEndsAt
        );
    }
}
