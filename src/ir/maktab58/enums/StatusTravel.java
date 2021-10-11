package ir.maktab58.enums;

public enum StatusTravel {
  WAITING("waiting"),
  DOING("doing");
  String status;

    StatusTravel(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
