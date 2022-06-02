package br.edu.aluno.projetofinal.auth.token.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class TokenPayload {
    @NonNull
    private final String subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenPayload that = (TokenPayload) o;
        return subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }
}
