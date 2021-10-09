package ir.maktab58;

public enum StatusTravel {
  PRESENCE("presence"),
  ABSENT("absent");
  String status;

    StatusTravel(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
