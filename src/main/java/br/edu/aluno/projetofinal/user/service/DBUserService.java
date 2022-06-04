package br.edu.aluno.projetofinal.user.service;

import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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

    @Override
    public Optional<User> save(@NonNull User user) {
        if (user.getLogin() == null) return Optional.empty();
        var userExists = userRepository.findByLogin(user.getLogin());
        if (userExists.isPresent()) throw new EntityExistsException();
        return Optional.of(userRepository.save(user));
    }
}
