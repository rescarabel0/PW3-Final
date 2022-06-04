package br.edu.aluno.projetofinal.room.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import br.edu.aluno.projetofinal.room.repository.RoomRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DBRoomService implements RoomService {
    @NonNull
    private final RoomRepository roomRepository;

    public DBRoomService(@NonNull RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> save(Room room) {
        return Optional.of(roomRepository.save(room));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> addToEquipmentList(@NonNull Long roomId, @NonNull Device device) {
        var optionalFoundRoom = roomRepository.findById(roomId);
        if (optionalFoundRoom.isEmpty()) throw new EntityNotFoundException();
        if (optionalFoundRoom.get().getDevices() == null) optionalFoundRoom.get().setDevices(List.of(device));
        else optionalFoundRoom.get().getDevices().add(device);
        return Optional.of(roomRepository.save(optionalFoundRoom.get()));
    }
}
