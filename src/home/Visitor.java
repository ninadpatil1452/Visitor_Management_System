package home;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import java.time.LocalTime;

public class Visitor {
    private SimpleStringProperty name;
    private SimpleStringProperty number;
    private SimpleStringProperty purpose;
    private LocalDate date;
    private LocalTime time;

    public Visitor(String visitorName , String visitorNumber , String visitorPurpose ,String visitorDate, String visitorTime)  {

        this.name = new SimpleStringProperty(visitorName);
        this.number = new SimpleStringProperty(visitorNumber);
        this.purpose = new SimpleStringProperty(visitorPurpose);
        this.date = LocalDate.parse(visitorDate);
        this.time = LocalTime.parse(visitorTime);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getPurpose() {
        return purpose.get();
    }

    public SimpleStringProperty purposeProperty() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose.set(purpose);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
