package br.edu.aluno.projetofinal.user.service.auth;

import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserService implements UserService {
    @NonNull
    private final UserRepository userRepository;

    public DBUserService(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByLogin(@NonNull String login) {
        return userRepository.findByLogin(login);
    }
}
