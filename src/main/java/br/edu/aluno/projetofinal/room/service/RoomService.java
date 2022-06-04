package br.edu.aluno.projetofinal.room.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    @NonNull
    Optional<Room> save(@NonNull Room room);

    @NonNull
    List<Room> findAll();

    @NonNull
    Optional<Room> addToEquipmentList(@NonNull Long roomId, @NonNull Device devices);
}
