package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        int availableParkingPosition = parkingLot.getAvailableParkingPosition();
        if (availableParkingPosition > 0) {
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
        for (ParkingTicket key : parkingLot.getCars().keySet()) {
            if (key == ticket) {
                Car car = parkingLot.getCars().get(key);
                parkingLot.getCars().remove(key);
                return car;
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
