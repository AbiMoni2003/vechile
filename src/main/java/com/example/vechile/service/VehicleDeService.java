package com.example.vechile.service;


import com.example.vechile.entity.Vehicle;
import com.example.vechile.repo.VehicleRepository;
import com.example.vechile.utility.EncryptionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleDeService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Existing save method to save encrypted vehicle data remains the same

    public Vehicle getDecryptedVehicleData(Long id) throws Exception {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();

            // Decrypt the fields
            vehicle.setVehicleNumber(EncryptionUtility.decrypt(vehicle.getVehicleNumber()));
            vehicle.setVehicleCategory(EncryptionUtility.decrypt(vehicle.getVehicleCategory()));
            vehicle.setVehicleType(EncryptionUtility.decrypt(vehicle.getVehicleType()));
            vehicle.setVehicleMake(EncryptionUtility.decrypt(vehicle.getVehicleMake()));
            vehicle.setEmissionTest(EncryptionUtility.decrypt(vehicle.getEmissionTest()));
            vehicle.setL1Test(EncryptionUtility.decrypt(vehicle.getL1Test()));

            return vehicle;
        } else {
            throw new Exception("Vehicle not found with ID: " + id);
        }
    }
}
