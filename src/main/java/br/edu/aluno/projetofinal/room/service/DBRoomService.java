package br.edu.aluno.projetofinal.room.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import br.edu.aluno.projetofinal.room.repository.RoomRepository;
import br.edu.aluno.projetofinal.user.domain.User;
import br.edu.aluno.projetofinal.user.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class DBRoomService implements RoomService {
    @NonNull
    private final RoomRepository roomRepository;
    @NonNull
    private final UserService userService;

    public DBRoomService(@NonNull RoomRepository roomRepository, @NonNull UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Room> save(@NonNull Room room, @NonNull String userLogin) throws Throwable {
        var user = findUserByLogin(userLogin);
        room.setUser(user);
        return Optional.of(roomRepository.save(room));
    }

    @Override
    public List<Room> findAllByUser(@NonNull String userLogin) throws Throwable {
        var user = findUserByLogin(userLogin);
        return roomRepository.findAllByUser(user);
    }

    @Override
    public Optional<Room> findOne(@NonNull Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void delete(@NonNull Long id) {
        var optionalFoundRoom = findOne(id);
        if (optionalFoundRoom.isEmpty()) throw new EntityNotFoundException();
        roomRepository.delete(optionalFoundRoom.get());
    }

    @Override
    public Optional<Room> addToDeviceList(@NonNull Long roomId, @NonNull Device device) {
        var optionalFoundRoom = roomRepository.findById(roomId);
        if (optionalFoundRoom.isEmpty()) throw new EntityNotFoundException();
        if (optionalFoundRoom.get().getDevices() == null) optionalFoundRoom.get().setDevices(List.of(device));
        else optionalFoundRoom.get().getDevices().add(device);
        return Optional.of(roomRepository.save(optionalFoundRoom.get()));
    }

    private User findUserByLogin(String userLogin) throws Throwable {
        return userService.findByLogin(userLogin).orElseThrow((Supplier<Throwable>) AuthenticationException::new);
    }
}
