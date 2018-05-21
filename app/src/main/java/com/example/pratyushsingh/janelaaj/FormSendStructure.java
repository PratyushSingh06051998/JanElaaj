package com.example.pratyushsingh.janelaaj;

/**
 * Created by pratyushsingh on 20/05/18.
 */

public class FormSendStructure {

    String fname;
    String lname;
    int age;
    String did;
    int id;


    public FormSendStructure(String fname, String lname, int age, String did, int id) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.did = did;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public FormSendStructure(String fname, String lname, int age, String did) {

        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.did = did;
    }
}
