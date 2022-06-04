package br.edu.aluno.projetofinal.room.controller;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import br.edu.aluno.projetofinal.room.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @NonNull
    private final RoomService roomService;

    public RoomController(@NonNull RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"/", ""})
    @NonNull
    public ResponseEntity<List<Room>> get() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PutMapping({"/devices/add/{id}", "/devices/add/{id}/"})
    @NonNull
    public ResponseEntity<Room> addToEquipmentsList(@NonNull @PathVariable Long id, @NonNull @RequestBody Device device) {
        try {
            var room = roomService.addToEquipmentList(id, device);
            if (room.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(room.get());
        } catch (EntityNotFoundException ignored) {
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping({"/", ""})
    @NonNull
    public ResponseEntity<Room> post(@NonNull @RequestBody Room room) {
        try {
            var savedRoom = roomService.save(room);
            if (savedRoom.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(savedRoom.get());
        } catch (EntityExistsException ignored) {
        }
        return ResponseEntity.badRequest().build();
    }
}
