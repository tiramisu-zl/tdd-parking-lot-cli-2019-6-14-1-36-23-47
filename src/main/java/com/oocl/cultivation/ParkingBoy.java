package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        parkingLot.getCars().put(ticket, car);

        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        for (ParkingTicket key: parkingLot.getCars().keySet()) {
            if (key == ticket) {
                return parkingLot.getCars().get(key);
            }
        }
        return null;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
