package br.edu.aluno.projetofinal.user.repository;

import br.edu.aluno.projetofinal.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findByLogin(@NonNull String login);
}
