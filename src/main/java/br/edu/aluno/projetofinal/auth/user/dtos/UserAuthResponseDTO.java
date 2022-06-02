package br.edu.aluno.projetofinal.auth.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class UserAuthResponseDTO {
    @NonNull
    private final String token;

    private final long expire;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthResponseDTO that = (UserAuthResponseDTO) o;
        return expire == that.expire && token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expire);
    }
}
