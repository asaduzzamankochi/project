package com.asaduzzamankochi.modelClass;

/**
 * Created by SaS on 6/18/2015.
 */
public class Doctor {
    int id;
    String name;
    String speciality;
    String address;
    String phone;
    String email;
    String notes;

    public Doctor(){

    }
    public Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Doctor(int id, String name, String speciality, String address, String phone, String email, String notes) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }
    //    public Doctor(int id, String notes, String email, String phone, String address, String speciality, String name) {
//        this.id = id;
//        this.notes = notes;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.speciality = speciality;
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString()  {
        return  name ;
    }
}
