package com.asaduzzamankochi.modelClass;

/**
 * Created by kochi on 29-Jun-15.
 */
public class Appointment {
    private int id;

    private String date;
    private String time;

    private int idProfile;
    private int idDoctor;

    public Appointment() {
    }

    public Appointment(int id, String date, String time, int idProfile, int idDoctor) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.idProfile = idProfile;
        this.idDoctor = idDoctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Override
    public String toString() {
        return date ;
    }

}
