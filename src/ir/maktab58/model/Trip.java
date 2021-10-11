package ir.maktab58.model;

import ir.maktab58.enums.StatusTrip;

import java.util.Objects;

public class Trip {
    private int tripNumber;
    private String origin;
        private String destination;
        private StatusTrip statusTrip;
        private  Drivers drivers;
        private Passengers passengers;

    public Trip() {
    }

    public Trip(String origin, String destination, StatusTrip statusTrip, Drivers drivers, Passengers passengers) {
        this.origin = origin;
       this. destination = destination;
        this.statusTrip = statusTrip;
        this.drivers = drivers;
        this.passengers = passengers;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
       this. destination = destination;
    }

    public StatusTrip getStatusTrip() {
        return statusTrip;
    }

    public void setStatusTrip(StatusTrip statusTrip) {
        this.statusTrip = statusTrip;
    }

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", origin='" + origin + '\'' +
                ", Destination='" + destination + '\'' +
                ", statusTrip=" + statusTrip +
                ", drivers=" + drivers +
                ", passengers=" + passengers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return tripNumber == trip.tripNumber && Objects.equals(origin, trip.origin) && Objects.equals(destination, trip.destination) && statusTrip == trip.statusTrip && Objects.equals(drivers, trip.drivers) && Objects.equals(passengers, trip.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripNumber, origin, destination, statusTrip, drivers, passengers);
    }
}
