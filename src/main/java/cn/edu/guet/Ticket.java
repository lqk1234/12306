package cn.edu.guet;

public class Ticket {
    private String trainNumber;
    private String formStation;
    private String toStation;
    private String departureTime;
    private String arrviaTime;
    private String duration;

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getFormStation() {
        return formStation;
    }

    public String getToStation() {
        return toStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrviaTime() {
        return arrviaTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setFormStation(String formStation) {
        this.formStation = formStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrviaTime(String arrviaTime) {
        this.arrviaTime = arrviaTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
