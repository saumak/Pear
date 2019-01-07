package com.pervasive.pear.pear;

import java.io.Serializable;

/**
 * Created by shruthi on 11/26/2018.
 */

public class User implements Serializable{
   private  String branch;
   private String email;
   private String location;
   private String name;
   private String pic;
   private String university;

    public User(String branch, String email, String location, String name, String pic, String university) {
        this.branch = branch;
        this.email = email;
        this.location = location;
        this.name = name;
        this.pic = pic;
        this.university = university;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
