package br.edu.aluno.projetofinal.user.service;

import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.function.Supplier;

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

    @Override
    public Optional<User> update(@NonNull User user) throws Throwable {
        if (user.getId() == null) throw new MissingRequestValueException("Usuário inválido. Falta id.");
        var optionalFoundUser = userRepository.findById(user.getId());
        if (optionalFoundUser.isEmpty()) throw new EntityNotFoundException();
        var foundUser = optionalFoundUser.get();

        foundUser.setName(Optional.ofNullable(user.getName())
                .orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Usuário inválido. Falta nome.")));
        foundUser.setLogin(Optional.ofNullable(user.getLogin())
                .orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Usuário inválido. Falta login.")));
        foundUser.setPassword(Optional.ofNullable(user.getPassword())
                .orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Usuário inválido. Falta senha.")));
        foundUser.setRoutineStartsAt(Optional.ofNullable(user.getRoutineStartsAt())
                .orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Usuário inválido. Falta horário início.")));
        foundUser.setRoutineEndsAt(Optional.ofNullable(user.getRoutineEndsAt())
                .orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Usuário inválido. Falta horário final.")));

        return Optional.of(userRepository.save(foundUser));
    }


}
