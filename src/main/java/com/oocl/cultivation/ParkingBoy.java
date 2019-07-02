package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private String lastErrorMessage;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().filter(lot -> lot.getAvailableParkingPosition() > 0).findFirst().orElse(null);
        if (parkingLot != null && parkingLot.getAvailableParkingPosition() > 0) {
            ParkingTicket ticket = new ParkingTicket();
            parkingLot.getCars().put(ticket, car);
            lastErrorMessage = null;
            return ticket;

        } else {
            lastErrorMessage = "The parking lot is full.";
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        ParkingLot parkingLot = parkingLots.stream().filter(lot -> {
            for (ParkingTicket key : lot.getCars().keySet()) {
                if (key == ticket) {
                    return true;
                }
            }
            return false;
        }).findAny().orElse(null);

        if (parkingLot != null) {
            for (ParkingTicket key : parkingLot.getCars().keySet()) {
                if (key == ticket) {
                    Car car = parkingLot.getCars().get(key);
                    parkingLot.getCars().remove(key);
                    return car;
                }
            }
        }
        if (ticket == null) {
            lastErrorMessage = "Please provide your parking ticket.";
        } else {
            lastErrorMessage = "Unrecognized parking ticket.";
        }
        return null;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
