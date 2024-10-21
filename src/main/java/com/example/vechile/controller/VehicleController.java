package com.example.vechile.controller;


import com.example.vechile.entity.Vehicle;
import com.example.vechile.service.VehicleDeService;
import com.example.vechile.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleDeService vehicleDeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVehicleData(@RequestParam("file") MultipartFile file) {
        try {
            vehicleService.saveEncryptedVehicleData(file);
            return ResponseEntity.ok("File uploaded and encrypted data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/decrypt/{id}")
    public ResponseEntity<?> getDecryptedVehicleData(@PathVariable Long id) {
        try {
            Vehicle vehicle = vehicleDeService.getDecryptedVehicleData(id);
            return ResponseEntity.ok(vehicle);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
