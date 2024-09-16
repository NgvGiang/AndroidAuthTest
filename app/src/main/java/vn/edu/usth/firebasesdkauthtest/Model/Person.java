package vn.edu.usth.firebasesdkauthtest.Model;


import java.util.HashMap;


public class Person {
    private String id;
    private String name;

    private String gender;

    private int birthYear;
    private HashMap<String, Job> jobs;

    public Person(String id,String name, String gender, int birthYear) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.jobs = new HashMap<>();
    }
    public Person() {
    }
    public String getId() {
        return id;
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
