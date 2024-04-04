package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

//         addParkingLot(name, address);
//         return parkingLotRepository1.save(addParkingLot(name, address));
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        parkingLot = parkingLotRepository1.save(parkingLot);

        return parkingLot;

    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
//        return addSpot(parkingLotId,numberOfWheels,pricePerHour);
        return spotRepository1.save(addSpot(parkingLotId,numberOfWheels,pricePerHour));

    }

    @Override
    public void deleteSpot(int spotId) {
        Optional<Spot> spotOptional = spotRepository1.findById(spotId);
        if(spotOptional.isPresent()){
            spotRepository1.delete(spotOptional.get());
        }

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Optional<Spot> spotOptional = spotRepository1.findById(spotId);
        if(spotOptional.isPresent()){
            Spot spot = spotOptional.get();
            spot.setPricePerHour(pricePerHour);
           Optional<ParkingLot> optionalParkingLot = parkingLotRepository1.findById(parkingLotId);
           if(optionalParkingLot.isPresent()){
               ParkingLot parkingLot = optionalParkingLot.get();
               spot.setParkingLot(parkingLot);
           }
           return spotRepository1.save(spot);
        }
        return null;

    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        Optional<ParkingLot> parkingLot = parkingLotRepository1.findById(parkingLotId);
        if(parkingLot.isPresent()){
            parkingLotRepository1.delete(parkingLot.get());
        }

    }
}
