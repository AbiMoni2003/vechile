package com.example.vechile.service;

import com.example.vechile.entity.Vehicle;
import com.example.vechile.repo.VehicleRepository;
import com.example.vechile.utility.EncryptionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void saveEncryptedVehicleData(MultipartFile file) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        Vehicle vehicle = new Vehicle();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                switch (parts[0].trim()) {
                    case "vehicleNumber":
                        vehicle.setVehicleNumber(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                    case "Vehiclecategory":
                        vehicle.setVehicleCategory(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                    case "VehicleType":
                        vehicle.setVehicleType(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                    case "VehicleMake":
                        vehicle.setVehicleMake(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                    case "EmissinTest":
                        vehicle.setEmissionTest(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                    case "L1Test":
                        vehicle.setL1Test(EncryptionUtility.encrypt(parts[1].trim()));
                        break;
                }
            }
        }

        vehicleRepository.save(vehicle); // Save encrypted vehicle data to database
    }
}
