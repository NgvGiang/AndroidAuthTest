package vn.edu.usth.firebasesdkauthtest.Model;


import java.util.HashMap;


public class Person {

    private String name;

    private String gender;

    private int birthYear;


    public Person(String name, String gender, int birthYear) {

        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;

    }
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
