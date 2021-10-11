package ir.maktab58.enums;

public enum StatusTrip {
    DOING("doing"),
    ENDED("ended");
    String trip;

    StatusTrip(String trip) {
        this.trip = trip;
    }

    public String getTrip() {
        return trip;
    }
}
