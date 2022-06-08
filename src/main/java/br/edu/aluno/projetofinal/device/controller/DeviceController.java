package br.edu.aluno.projetofinal.device.controller;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.device.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/device"})
@CrossOrigin(origins = {"*"})
public class DeviceController {
    @NonNull
    private final DeviceService deviceService;

    public DeviceController(@NonNull DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Device>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> save(@NonNull @RequestBody Device device) {
        try {
            var deviceRes = deviceService.save(device);
            if (deviceRes.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(deviceRes.get());
        } catch (Throwable ignored) {
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @NonNull
    public ResponseEntity<?> delete(@NonNull @PathVariable Long id) {
        try {
            deviceService.deleteByID(id);
            return ResponseEntity.ok().build();
        } catch (Throwable ignored) {
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping({"/{id}", "/{id}/"})
    @NonNull
    public ResponseEntity<Device> update(@NonNull @RequestBody Device device, @NonNull @PathVariable Long id) {
        try {
            var updatedDevice = deviceService.update(device, id);
            if (updatedDevice.isEmpty()) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(updatedDevice.get());
        } catch (Throwable ignored) {
        }
        return ResponseEntity.badRequest().build();
    }
}
