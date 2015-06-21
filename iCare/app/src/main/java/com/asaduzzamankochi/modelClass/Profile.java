package com.asaduzzamankochi.modelClass;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class Profile {
    int id;
    String name;
    String age;
    String gender;
    String bloodGroup;
    String height;
    String weight;
    String phoneNo;
    String category;

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Profile() {
    }

    public Profile(int id, String name, String birthDay, String gender, String bloodGroup, String height, String weight, String phoneNo, String category) {
        this.id = id;
        this.name = name;
        this.age = birthDay;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.phoneNo = phoneNo;
        this.category = category;
    }

    public Profile(String category) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.phoneNo = phoneNo;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
