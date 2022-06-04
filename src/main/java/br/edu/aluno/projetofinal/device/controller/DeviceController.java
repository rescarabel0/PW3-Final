package br.edu.aluno.projetofinal.device.controller;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.device.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/device", "/device/"})
public class DeviceController {
    @NonNull
    private final DeviceService deviceService;

    public DeviceController(@NonNull DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }
}
