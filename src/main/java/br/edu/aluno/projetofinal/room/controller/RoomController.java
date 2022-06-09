package br.edu.aluno.projetofinal.room.controller;

import br.edu.aluno.projetofinal.auth.user.UserAuthentication;
import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.room.domain.Room;
import br.edu.aluno.projetofinal.room.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = {"*"})
public class RoomController {
    @NonNull
    private final RoomService roomService;

    public RoomController(@NonNull RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"/", ""})
    @NonNull
    public ResponseEntity<List<Room>> get() {
        var authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        try {
            var rooms = roomService.findAllByUser(authentication.getLogin());
            return ResponseEntity.ok(rooms);
        } catch (Throwable e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping({"/{id}", "/{id}/"})
    @NonNull
    public ResponseEntity<Room> getOne(@NonNull @PathVariable Long id) {
        var foundRoom = roomService.findOne(id);
        if (foundRoom.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundRoom.get());
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @NonNull
    public ResponseEntity<?> delete(@NonNull @PathVariable Long id) {
        try {
            roomService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ignored) {
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping({"/devices/add/{id}", "/devices/add/{id}/"})
    @NonNull
    public ResponseEntity<Room> addToEquipmentsList(@NonNull @PathVariable Long id, @NonNull @RequestBody Device device) {
        try {
            var room = roomService.addToDeviceList(id, device);
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
            var authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
            var savedRoom = roomService.save(room, authentication.getLogin());
            if (savedRoom.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(savedRoom.get());
        } catch (EntityExistsException ignored) {
        } catch (Throwable ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.badRequest().build();
    }
}
