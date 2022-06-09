package br.edu.aluno.projetofinal.room.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    @NonNull
    Optional<Room> save(@NonNull Room room, @NonNull String userLogin) throws Throwable;

    @NonNull
    List<Room> findAllByUser(@NonNull String userLogin) throws Throwable;

    @NonNull
    Optional<Room> findOne(@NonNull Long id);

    @NonNull
    void delete(@NonNull Long id);

    @NonNull
    Optional<Room> addToDeviceList(@NonNull Long roomId, @NonNull Device device);

    @NonNull
    void removeFromDeviceList(@NonNull Room room, @NonNull Device device) throws MissingRequestValueException;
}
