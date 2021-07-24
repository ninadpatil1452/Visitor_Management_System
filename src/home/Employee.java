package home;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Employee {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty number;
    private SimpleStringProperty department;
    private SimpleStringProperty post;
    private LocalDate date;
    private LocalTime time;

    public Employee(Integer id, String name, String number,
                    String department, String post) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleStringProperty(number);
        this.department = new SimpleStringProperty(department);
        this.post = new SimpleStringProperty(post);
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

    public Employee(Integer id, String date, String time, String name, String number, String department){

        this.id=new SimpleIntegerProperty(id);
        this.date= LocalDate.parse(date);
        this.time = LocalTime.parse(time);
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleStringProperty(number);
        this.department = new SimpleStringProperty(department);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getPost() {
        return post.get();
    }

    public SimpleStringProperty postProperty() {
        return post;
    }

    public void setPost(String post) {
        this.post.set(post);
    }
}
